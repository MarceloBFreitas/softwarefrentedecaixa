package br.com.ecf;

import bemajava.*;
import static com.sun.org.apache.xalan.internal.xsltc.compiler.util.Type.String;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class BemaECF {

    private static int iRetorno;
    private BemaInteger iACK, iST1, iST2;
    private static String cpf;

    public static void analisaRetorno() {
        switch (iRetorno) {
            case 0:
                JOptionPane.showMessageDialog(null, "Erro de Comunicação!");
                break;
            case -1:
                JOptionPane.showMessageDialog(null, "Erro de Execução da Função!");
                break;
            case -2:
                JOptionPane.showMessageDialog(null, "Parâmetro Inválido!");
                break;
            case -3:
                JOptionPane.showMessageDialog(null, "Alíquota não Programada!");
                break;
            case -4:
                JOptionPane.showMessageDialog(null, "Arquivo BemaFI32 não foi encontrado!");
                break;
            case -5:
                JOptionPane.showMessageDialog(null, "Erro ao abrir porta de Comunicação!");
                break;
            case -6:
                JOptionPane.showMessageDialog(null, "Impressora Desligada ou Desconectada");
                break;
            case -7:
                JOptionPane.showMessageDialog(null, "Banco não cadastrado no Arquivo BemaFI32.ini");
                break;
            case -8:
                JOptionPane.showMessageDialog(null, "Erro ao Criar ou Gravar no Arquivo!");
                break;
            case -18:
                JOptionPane.showMessageDialog(null, "Não foi possível abrir arquivos INTPOS.001 !");
                break;
            case -19:
                JOptionPane.showMessageDialog(null, "Parâmetros Diferentes");
                break;
            case -20:
                JOptionPane.showMessageDialog(null, "Transação cancelada pelo Operador!");
                break;
            case -21:
                JOptionPane.showMessageDialog(null, "A Transação não Aprovada");
                break;
            case -22:
                JOptionPane.showMessageDialog(null, "Não foi possivél terminar a impressão");
                break;
            case -23:
                JOptionPane.showMessageDialog(null, "Não foi possivél terminar a operação!");
                break;
            case -24:
                JOptionPane.showMessageDialog(null, "Forma de Pagamento não Programada");
                break;
            case -25:
                JOptionPane.showMessageDialog(null, "Totalizador não fiscal não programado");
                break;
            case -26:
                JOptionPane.showMessageDialog(null, "Transação já efetuada");
                break;
            case -28:
                JOptionPane.showMessageDialog(null, "Não há informações para serem impressas!");
                break;

        }
    }

    public static void retornoImpressora() {
        BemaInteger iACK = new BemaInteger();
        BemaInteger iST1 = new BemaInteger();
        BemaInteger iST2 = new BemaInteger();

        iRetorno = Bematech.RetornoImpressora(iACK, iST1, iST2);
        //Verificando o iST1
        if (iACK.number == 0) {
            if (iST1.number >= 128) {
                iST1.number = iST1.number - 128;
                JOptionPane.showMessageDialog(null, "Fim de Papel");
            }
            if (iST1.number >= 64) {
                iST1.number = iST1.number - 64;
                JOptionPane.showMessageDialog(null, "Pouco Papel");
            }
            if (iST1.number >= 36) {
                iST1.number = iST1.number - 32;
                JOptionPane.showMessageDialog(null, "Erro no Relógio");
            }
            if (iST1.number >= 16) {
                iST1.number = iST1.number - 16;
                JOptionPane.showMessageDialog(null, "Impressora com ERRO");
            }
            if (iST1.number >= 8) {
                iST1.number = iST1.number - 8;
                JOptionPane.showMessageDialog(null, "CMD não Iniciado com ESC");
            }
            if (iST1.number >= 4) {
                iST1.number = iST1.number - 4;
                JOptionPane.showMessageDialog(null, "Comando Inexistente");
            }
            if (iST1.number >= 2) {
                iST1.number = iST1.number - 2;
                JOptionPane.showMessageDialog(null, "Cupom Aberto");
            }
            if (iST1.number >= 1) {
                iST1.number = iST1.number - 1;
                JOptionPane.showMessageDialog(null, "Nº de parâmetros Inválidos");
            }
        }
        //Verificando o iST2
        if (iACK.number == 0) {
            if (iST2.number >= 128) {
                iST2.number = iST2.number - 128;
                JOptionPane.showMessageDialog(null, "Tipo de Parâmetro Inválido");
            }
            if (iST2.number >= 64) {
                iST2.number = iST2.number - 64;
                JOptionPane.showMessageDialog(null, "Memória Fiscal Lotada");
            }
            if (iST2.number >= 36) {
                iST2.number = iST2.number - 32;
                JOptionPane.showMessageDialog(null, "CMOS não volátil");
            }
            if (iST2.number >= 16) {
                iST2.number = iST2.number - 16;
                JOptionPane.showMessageDialog(null, "Alíquota não Programada");
            }
            if (iST2.number >= 8) {
                iST2.number = iST2.number - 8;
                JOptionPane.showMessageDialog(null, "Alíquotas Lotadas");
            }
            if (iST2.number >= 4) {
                iST2.number = iST2.number - 4;
                JOptionPane.showMessageDialog(null, "Cancelamento não Permitido");
            }
            if (iST2.number >= 2) {
                iST2.number = iST2.number - 2;
                JOptionPane.showMessageDialog(null, "CNPJ/IE não programados");
            }
            if (iST2.number >= 1) {
                iST2.number = iST2.number - 1;
                JOptionPane.showMessageDialog(null, "Comando não executado");
            }
        }

    }

    public static void leituraX() {

        if (JOptionPane.showConfirmDialog(null, "Deseja Emitir a Leitura X ?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.LeituraX();
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void reducaoZ() {

        if (JOptionPane.showConfirmDialog(null, "Deseja Emitir a Redução Z? - Atenção, Caixa será encerrado !", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.ReducaoZ(null, null);
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void abreCupom() {
        iRetorno = Bematech.AbreCupom(null);//deixar null se não for colocar cnpj ou cpf
        analisaRetorno();
        retornoImpressora();
    }

    //o método fecha cupom vai inicializar com o método IniciaFechamentodeCupom()
    public static void fechaCupom(String AcrescimoDesconto, String TipoAcrescimoDesconto, String ValorAcrescimoDesconto,
            String mensagem, ArrayList formasdepagamento) {
        iRetorno = Bematech.IniciaFechamentoCupom(AcrescimoDesconto, TipoAcrescimoDesconto, ValorAcrescimoDesconto);
        for (int i = 0; i < formasdepagamento.size(); i++) {
            iRetorno = Bematech.EfetuaFormaPagamento((String) formasdepagamento.get(0), (String) formasdepagamento.get(1));
        }
        iRetorno = Bematech.TerminaFechamentoCupom(mensagem);
        analisaRetorno();
        retornoImpressora();
    }

    public static void abrirPortaSerial() {
        iRetorno = Bematech.AbrePortaSerial();
        analisaRetorno();
        retornoImpressora();

    }

    public static void fechaPortaSerial() {
        iRetorno = Bematech.FechaPortaSerial();
        analisaRetorno();
        retornoImpressora();

    }

    public static void horarioVerao() {

        if (JOptionPane.showConfirmDialog(null, "Deseja Sair/Entrar no Horário de Verão?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.ProgramaHorarioVerao();
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void memoriaFiscalPorDatal(String data1, String data2) {
        if (JOptionPane.showConfirmDialog(null, "Deseja emitir relatório de Memória Fiscal?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.LeituraMemoriaFiscalData(data2, data1);
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void memoriaFiscalPorReducao(String red1, String red2) {
        if (JOptionPane.showConfirmDialog(null, "Deseja emitir relatório de Memória Fiscal por Redução?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.LeituraMemoriaFiscalReducao(red1, red2);
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void cancelaCupom() {
        if (JOptionPane.showConfirmDialog(null, "Deseja cancelar o cupom anterior?", "Pergunta do Sistema", JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
            iRetorno = Bematech.CancelaCupom();
            analisaRetorno();
            retornoImpressora();
        }
    }

    public static void vendeItem(String codigo, String descricao, String aliquota, String tipoqtde, String qntde,
            Integer casasdecimais, String valoruni, String tipodesconto, String valordesconto) {

        iRetorno = Bematech.VendeItem(codigo, descricao, aliquota, tipoqtde, qntde, casasdecimais, valoruni, tipodesconto, valordesconto);
        analisaRetorno();
        retornoImpressora();
    }
    
    public static void cancelaItemAnterior() {
        iRetorno = Bematech.CancelaItemAnterior();
        analisaRetorno();
        retornoImpressora();
    }

    public static void cancelaItemGenerico(String item) {
        iRetorno = Bematech.CancelaItemGenerico(item);
        analisaRetorno();
        retornoImpressora();
    }
}
