package challenge;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;

public class Estacionamento {


    private final Queue<Carro> ESTACIONAMENTO = new LinkedList<>();
    private static final int NUMERO_VAGAS = 10;

    public void estacionar(Carro carro) {
        this.validaCarro(carro);
        this.validaMotorista(carro.getMotorista());

        if(this.isLotado()) {
            if(carro.getMotorista().isSenior() || this.isProximoMotoristaSenior()) {
                this.liberaVaga(carro);
                return;
            }
            ESTACIONAMENTO.poll();
        }
        ESTACIONAMENTO.add(carro);
    }

    public void liberaVaga(Carro carro) {
        Optional<Carro> carroParaRemover = ESTACIONAMENTO.stream()
                .filter(c -> !c.getMotorista().isSenior())
                .findFirst();

        if(carroParaRemover.isPresent()) {
            ESTACIONAMENTO.remove(carroParaRemover.get());
            ESTACIONAMENTO.add(carro);
            return;
        }
        throw new EstacionamentoException(Mensagem.ESTACIONAMENTO_LOTADO);
    }

    public boolean isProximoMotoristaSenior() {
        return Optional.ofNullable(this.ESTACIONAMENTO.peek())
                .map(Carro::getMotorista)
                .map(Motorista::isSenior)
                .orElse(false);

    }

    public boolean isLotado() {
        return ESTACIONAMENTO.size() >= NUMERO_VAGAS;
    }

    public int carrosEstacionados() {
        return ESTACIONAMENTO.size();
    }

    public boolean carroEstacionado(Carro carro) {
        return ESTACIONAMENTO.contains(carro);
    }

    private void validaCarro(Carro carro) {
        if(carro.isAutonomo()) throw new EstacionamentoException(Mensagem.CARRO_AUTONOMO);
    }

    private void validaMotorista(Motorista motorista) {
        if(motorista.isMenorIdade()) throw new EstacionamentoException(Mensagem.MENOR_IDADE);
        if(motorista.isHabilitacaoSuspensa()) throw new EstacionamentoException(Mensagem.HABILITACAO_SUSPENSA);
    }
}
