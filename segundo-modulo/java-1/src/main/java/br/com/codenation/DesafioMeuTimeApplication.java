package br.com.codenation;

import br.com.codenation.domain.entity.Jogador;
import br.com.codenation.domain.entity.Time;
import br.com.codenation.domain.repository.Repository;
import br.com.codenation.exceptions.CapitaoNaoInformadoException;
import br.com.codenation.exceptions.JogadorNaoEncontradoException;
import br.com.codenation.exceptions.TimeNaoEncontradoException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class DesafioMeuTimeApplication implements MeuTimeInterface {

	private final Repository<Time> TIMES = new Repository<>();
	private final Repository<Jogador> JOGADORES = new Repository<>();

	public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
		TIMES.cadastrar(new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario));
	}

	public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {
		TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		JOGADORES.cadastrar(new Jogador(id, idTime, nome, dataNascimento, nivelHabilidade, salario));
	}

	public void definirCapitao(Long idJogador) {
		Jogador jogador = JOGADORES.buscarPorId(idJogador).orElseThrow(JogadorNaoEncontradoException::new);
		Time time = TIMES.buscarPorId(jogador.getIdTime()).orElseThrow(TimeNaoEncontradoException::new);

		time.setCapitao(jogador.getId());
	}

	public Long buscarCapitaoDoTime(Long idTime) {
		Time time = TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		time.temCapitao();
		return time.getCapitao();
	}

	public String buscarNomeJogador(Long idJogador) {
		return JOGADORES.buscarPorId(idJogador).map(Jogador::getNome).orElseThrow(JogadorNaoEncontradoException::new);
	}

	public String buscarNomeTime(Long idTime) {
		TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		return TIMES.buscarPorId(idTime).map(Time::getNome).orElseThrow(TimeNaoEncontradoException::new);
	}

	public List<Long> buscarJogadoresDoTime(Long idTime) {
		TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		return JOGADORES.buscar(j -> j.getIdTime().equals(idTime))
				.map(Jogador::getId)
				.collect(Collectors.toList());
	}

	public Long buscarMelhorJogadorDoTime(Long idTime) {
		return JOGADORES.buscar(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getNivelHabilidade))
				.map(Jogador::getId)
				.orElseThrow(TimeNaoEncontradoException::new);
	}

	public Long buscarJogadorMaisVelho(Long idTime) {
		TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		return JOGADORES.buscar(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getDataNascimento).reversed())
				.map(Jogador::getId)
				.orElseThrow(JogadorNaoEncontradoException::new);
	}

	public List<Long> buscarTimes() {
		return TIMES.buscar().map(Time::getId).collect(Collectors.toList());
	}

	public Long buscarJogadorMaiorSalario(Long idTime) {
		TIMES.buscarPorId(idTime).orElseThrow(TimeNaoEncontradoException::new);
		return JOGADORES.buscar(j -> j.getIdTime().equals(idTime))
				.max(Comparator.comparing(Jogador::getSalario))
				.map(Jogador::getId)
				.orElseThrow(JogadorNaoEncontradoException::new);
	}

	public BigDecimal buscarSalarioDoJogador(Long idJogador) {
		return JOGADORES.buscarPorId(idJogador)
				.map(Jogador::getSalario)
				.orElseThrow(JogadorNaoEncontradoException::new);
	}

	public List<Long> buscarTopJogadores(Integer top) {
		return JOGADORES.buscar()
				.sorted(Comparator.comparing(Jogador::getNivelHabilidade).reversed().thenComparing(Jogador::getId))
				.map(Jogador::getId)
				.limit(top)
				.collect(Collectors.toList());
	}

}
