

import java.util.InputMismatchException;

public class DataUtil {
    public static boolean validaCnpj(String CNPJ) {
// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
        if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
                CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
                CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
                CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
                CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
                (CNPJ.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;

// "try" - protege o código para eventuais erros de conversao de tipo (int)
        try {
// Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
// converte o i-ésimo caractere do CNPJ em um número:
// por exemplo, transforma o caractere '0' no inteiro 0
// (48 eh a posição de '0' na tabela ASCII)
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else dig13 = (char) ((11 - r) + 48);

// Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (CNPJ.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else dig14 = (char) ((11 - r) + 48);

// Verifica se os dígitos calculados conferem com os dígitos informados.
            if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
                return (true);
            else return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }

    public static String imprimeCNPJ(String CNPJ) {
// máscara do CNPJ: 99.999.999.9999-99
        return (CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
                CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
                CNPJ.substring(12, 14));
    }

    public static boolean validaEan13(String ean13) {
        if (ean13.length() != 13) {
            return false;
        }
        // String pais = ean13.substring(0, 3);
        // String empresa = ean13.substring(3, 8);
        // String produto = ean13.substring(8, 12);
        // String digitoVerificador = ean13.substring(12, 13);
        int somapar = 0;
        int somaimpar = 0;
        // Vamos supor que estamos usando o c�digo fict�cio de: 7891991010023.
        // Adicione todos os d�gitos �mpares (d�gitos na posi��o 1,
        // 3, 5, 7, 9 e 11)
        // 7 + 9 + 1+9+9 + 1 + 1= 37
        for (int i = 1; i <= 12; i++) {
            int dig = 0;
            try {
                dig = Integer.parseInt(Character.toString(ean13.charAt(i - 1)));
            } catch (NumberFormatException e) {
                return false;
            }
            if (i % 2 == 0) {
                somapar += dig * 3;
            } else {
                somaimpar += dig;
            }
        }
        // Adicione todos os d�gitos pares (d�gitos na posi��o 2,
        // 4, 6, 8 e 10).
        // 8+2= 10
        // Multiplique por 3.
        // 10* 3 = 30

        // Some os resultados das etapas 1 e 3.
        // 37 + 30 = 67
        int soma = somapar + somaimpar;
        // Determine o n�mero que deve ser adicionado ao resultado do passo 4
        int dig = 0;
        boolean naoencontrou = true;
        while (naoencontrou) {
            int temp = soma + dig;
            if ((temp % 10) == 0) {
                naoencontrou = false;
            } else {
                dig++;
            }
        }
        // para criar um m�ltiplo de 10.
        // 67 + 3 = 70
        // Portanto, o d�gito verificador � 3.
        int digitoVerficador = Integer.parseInt(Character.toString(ean13
                .charAt(12)));
        if (digitoVerficador == dig) {
            return true;
        }
        return false;
    }
    
    public static boolean validaEan14(String ean14) {
        if (ean14.length() != 14) {
            return false;
        }
        // String pais = ean13.substring(0, 3);
        // String empresa = ean13.substring(3, 8);
        // String produto = ean13.substring(8, 12);
        // String digitoVerificador = ean13.substring(12, 13);
        int somapar = 0;
        int somaimpar = 0;
        // Vamos supor que estamos usando o c�digo fict�cio de: 7891991010023.
        // Adicione todos os d�gitos �mpares (d�gitos na posi��o 1,
        // 3, 5, 7, 9 e 11)
        // 7 + 9 + 1+9+9 + 1 + 1= 37
        for (int i = 1; i <= 13; i++) {
            int dig = 0;
            try {
                dig = Integer.parseInt(Character.toString(ean14.charAt(i - 1)));
            } catch (NumberFormatException e) {
                return false;
            }
            if (i % 2 == 0) {
                somapar += dig * 3;
            } else {
                somaimpar += dig;
            }
        }
        // Adicione todos os d�gitos pares (d�gitos na posi��o 2,
        // 4, 6, 8 e 10).
        // 8+2= 10
        // Multiplique por 3.
        // 10* 3 = 30

        // Some os resultados das etapas 1 e 3.
        // 37 + 30 = 67
        int soma = somapar + somaimpar;
        // Determine o n�mero que deve ser adicionado ao resultado do passo 4
        int dig = 0;
        boolean naoencontrou = true;
        while (naoencontrou) {
            int temp = soma + dig;
            if ((temp % 10) == 0) {
                naoencontrou = false;
            } else {
                dig++;
            }
        }
        // para criar um m�ltiplo de 10.
        // 67 + 3 = 70
        // Portanto, o d�gito verificador � 3.
        int digitoVerficador = Integer.parseInt(Character.toString(ean14
                .charAt(13)));
        if (digitoVerficador == dig) {
            return true;
        }
        return false;
    }

    public static boolean validaUPC(String upc) {
        if (upc.length() != 12) {
            return false;
        }
        // String pais = ean13.substring(0, 3);
        // String empresa = ean13.substring(3, 8);
        // String produto = ean13.substring(8, 12);
        // String digitoVerificador = ean13.substring(12, 13);
        int somapar = 0;
        int somaimpar = 0;
        // Vamos supor que estamos usando o c�digo fict�cio de: 7891991010023.
        // Adicione todos os d�gitos �mpares (d�gitos na posi��o 1,
        // 3, 5, 7, 9 e 11)
        // 7 + 9 + 1+9+9 + 1 + 1= 37
        for (int i = 1; i <= 11; i++) {
            int dig = 0;
            try {
                dig = Integer.parseInt(Character.toString(upc.charAt(i - 1)));
            } catch (NumberFormatException e) {
                return false;
            }
            if (i % 2 == 0) {
                somapar += dig * 3;
            } else {
                somaimpar += dig;
            }
        }
        // Adicione todos os d�gitos pares (d�gitos na posi��o 2,
        // 4, 6, 8 e 10).
        // 8+2= 10
        // Multiplique por 3.
        // 10* 3 = 30

        // Some os resultados das etapas 1 e 3.
        // 37 + 30 = 67
        int soma = somapar + somaimpar;
        // Determine o n�mero que deve ser adicionado ao resultado do passo 4
        int dig = 0;
        boolean naoencontrou = true;
        while (naoencontrou) {
            int temp = soma + dig;
            if ((temp % 10) == 0) {
                naoencontrou = false;
            } else {
                dig++;
            }
        }
        // para criar um m�ltiplo de 10.
        // 67 + 3 = 70
        // Portanto, o d�gito verificador � 3.
        int digitoVerficador = Integer.parseInt(Character.toString(upc
                .charAt(11)));
        if (digitoVerficador == dig) {
            return true;
        }
        return false;
    }
    
    public static boolean validaEndereco(String endereco) {
        if (endereco.length() == 0) {
            return false;
        }
        return true;
    }

}
