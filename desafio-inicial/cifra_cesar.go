package main

import (
	"bytes"
	"crypto/sha1"
	"encoding/hex"
	"encoding/json"
	"fmt"
	"io"
	"io/ioutil"
	"mime/multipart"
	"net/http"
	"os"
	"sort"
	"strings"
)

const (
	BASE_URL      = "https://api.codenation.dev/v1/challenge/dev-ps"
	TOKEN         = "[ INSIRA SEU TOKEN ]"
	ALPHABET_SIZE = 26
)

var brAlphabet = []string{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"}

type Answer struct {
	Shift           int    `json:"numero_casas"`
	Token           string `json:"token"`
	Encoded         string `json:"cifrado"`
	Decoded         string `json:"decifrado"`
	EncodedAbstract string `json:"resumo_criptografico"`
}

func check(e error) {
	if e != nil {
		panic(e)
	}
}

func main() {
	body := getData()

	answer := Answer{}
	json.Unmarshal(body, &answer)

	answer.Decoded = decode(answer.Encoded, answer.Shift)
	answer.EncodedAbstract = hash(answer.Decoded)

	f, err := json.Marshal(answer)
	check(err)

	ioutil.WriteFile("answer.json", f, 0644)
	sendFile("answer.json")

}

func hash(text string) string {
	h := sha1.New()
	h.Write([]byte(text))
	return hex.EncodeToString(h.Sum(nil))
}

func decode(Encoded string, shift int) string {
	var decoded strings.Builder

	for _, char := range Encoded {
		char := string(char)

		charIndex := sort.SearchStrings(brAlphabet, char)

		if charIndex == 0 && brAlphabet[0] != char {
			decoded.WriteString(char)
			continue
		}

		charIndex = (charIndex - shift) % ALPHABET_SIZE

		if charIndex < 0 {
			charIndex += ALPHABET_SIZE
		}

		decoded.WriteString(brAlphabet[charIndex])
	}

	return decoded.String()
}

func getData() []byte {
	request, err := http.NewRequest("GET", BASE_URL+"/generate-data", nil)
	check(err)

	response := doRequest(request)
	defer response.Body.Close()

	body, err := ioutil.ReadAll(response.Body)
	check(err)

	return body
}

func sendFile(fileName string) {
	file, err := os.Open(fileName)
	check(err)

	fileContent, err := ioutil.ReadAll(file)
	check(err)

	defer file.Close()

	var requestBody bytes.Buffer
	writer := multipart.NewWriter(&requestBody)
	multipartWriter, err := writer.CreateFormFile("answer", fileName)
	check(err)
	multipartWriter.Write(fileContent)
	io.Copy(multipartWriter, file)
	writer.Close()

	request, err := http.NewRequest("POST", BASE_URL+"/submit-solution", &requestBody)
	request.Header.Add("Content-Type", writer.FormDataContentType())
	check(err)

	fmt.Println("Request:\n", &requestBody)
	response := doRequest(request)
	defer response.Body.Close()

	responseBody, err := ioutil.ReadAll(response.Body)
	check(err)

	var bodyString string
	json.Unmarshal(responseBody, &bodyString)

	fmt.Println("Response:\n", string(responseBody))
	fmt.Println(response.StatusCode)

}

func doRequest(request *http.Request) *http.Response {
	client := http.Client{}
	queryString := request.URL.Query()
	queryString.Add("token", TOKEN)
	request.URL.RawQuery = queryString.Encode()

	response, err := client.Do(request)
	check(err)

	return response
}
