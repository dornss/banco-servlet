package br.com.ibm.bancoservlet.test;

import br.com.ibm.bancoservlet.GlobalExceptionHandler.ContaInvalidaException;
import br.com.ibm.bancoservlet.GlobalExceptionHandler.SaldoInsuficienteException;
import br.com.ibm.bancoservlet.models.Cliente;
import br.com.ibm.bancoservlet.models.ContaCorrente;
import br.com.ibm.bancoservlet.services.ContaCorrenteServiceImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class ContaCorrenteServiceImplTest {

    private ContaCorrenteServiceImpl contaService;

    @Before
    public void setUp() {
        contaService = new ContaCorrenteServiceImpl(new ArrayList<>());

    }

    @Test
    public void criarContaTest() {
        ContaCorrente contaCorrente = new ContaCorrente("00001", 1000, new Cliente("123456789", "Gabriel"));
        assertNotNull(contaCorrente);
        assertEquals("Gabriel Dornelas", contaCorrente.getTitular().getNome());
        assertEquals("11503155393", contaCorrente.getTitular().getCpf());
        assertEquals(100.0, contaCorrente.getSaldo(), 0.001);
    }

    @Test
    public void depositarTeste() throws ContaInvalidaException {
        ContaCorrente contaCorrente = new ContaCorrente("00001", 1000, new Cliente("123456789", "Gabriel"));
        contaService.depositar(contaCorrente.getNumeroConta(), 100.0);

        assertEquals(100.0, contaCorrente.getSaldo(), 0.001);
    }

    @Test
    public void sacarTeste() throws ContaInvalidaException, SaldoInsuficienteException {
        ContaCorrente contaCorrente = new ContaCorrente("00001", 1000, new Cliente("123456789", "Gabriel"));

        contaService.depositar(contaCorrente.getNumeroConta(), 100.0);
        contaCorrente.sacar(50.0);

        assertEquals(50.0, contaCorrente.getSaldo(), 0.0001);

    }

    @Test
    public void sacandoComSaldoInsuficienteTeste() throws ContaInvalidaException {

        ContaCorrente contaCorrente = new ContaCorrente("00001", 1000, new Cliente("123456789", "Gabriel"));
        contaService.depositar(contaCorrente.getNumeroConta(), 50.0);

        assertThrows(SaldoInsuficienteException.class, () -> contaService.sacar(contaCorrente.getNumeroConta(), 100));
    }

    @Test
    public void transferirTeste() throws ContaInvalidaException, SaldoInsuficienteException {
        ContaCorrente origem = new ContaCorrente("00001", 1000, new Cliente("123456789", "Gabriel"));
        ContaCorrente destino = new ContaCorrente("00002", 1000, new Cliente("0987654321", "Gabriela"));

        contaService.transferir(origem.getNumeroConta(), destino.getNumeroConta(), 1000);

        assertEquals(100.0, origem.getSaldo(), 0.001);
        assertEquals(200.0, destino.getSaldo(), 0.001);
    }
}