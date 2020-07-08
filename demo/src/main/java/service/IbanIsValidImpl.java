package service;

import java.util.Arrays;

public class IbanIsValidImpl {
    public boolean IsValid (String input) {
        String[] IBAN_CODE_COUNTRY = { "AL", "AD", "AT", "AZ", "BH", "BY", "BE", "BA", "BR", "BG", "CR", "HR", "CY", "CZ", "DK", "DO", "TL", "EG", "EE", "FO",
                                        "FI", "FR", "GE", "DE", "GI", "GR", "GL", "GT", "HU", "IS", "IQ", "IE", "IL", "IT", "JO", "KZ", "XK", "KW", "LV", "LB",
                                        "LI", "LT", "LU", "MK", "MT", "MR", "MU", "MC", "MD", "ME", "NL", "NO", "PK", "PS", "PL", "PT", "QA", "RO", "LC", "SM",
                                        "SA", "RS", "SC", "SK", "SI", "ES", "SE", "CH", "TN", "TR", "UA", "AE", "GB", "VA", "VG", "DZ", "AO", "BJ", "BF", "BI",
                                        "CM", "CV", "IR", "CI", "MG", "ML", "MZ", "SN", "KM", "TD", "CG", "GA", "HN", "MA", "NI", "NE", "TG"};
        int[] IBAN_CODE_LENGTHS = { 28, 24, 20, 28, 22, 28, 16, 20, 29, 22, 22, 21, 28, 24, 18, 28, 23, 29, 20, 18, 18, 27, 22, 22, 23, 27, 18, 28, 28, 26, 23,
                                    22, 23, 27, 30, 20, 20, 30, 21, 28, 21, 20, 20, 19, 31, 27, 30, 27, 24, 22, 18, 15, 24, 29, 28, 25, 29, 24, 32, 27, 24, 22,
                                    31, 24, 19, 24, 24, 21, 24, 26, 29, 23, 22, 22, 24, 24, 25, 28, 28, 16, 27, 25, 26, 28, 27, 28, 25, 28, 27, 27, 27, 27, 28,
                                    28, 32, 28, 28};

        String iban = input.toUpperCase();
        if(iban.matches("/^([A-Z]{2})(\\d{2})([A-Z\\d]+)$/")) //checks IBAN format
        {
            String[] code = new String[3];
            code[1] = iban.substring(0, 2);
            code[2] = iban.substring(2, 4);
            code[3] = iban.substring(4);
            int index = Arrays.asList(IBAN_CODE_COUNTRY).indexOf(code[1]);
            if ( index == -1 || IBAN_CODE_LENGTHS[index] != iban.length() ) return false; //checks if country code is valid, and if so, checks the IBAN length
            else {
                for(int i = 1; i <= 3; i += 2) {
                    StringBuilder codeBuild = new StringBuilder();
                    for (int j = 0; i < code[i].length(); j++) {
                        char tmpChar = code[3].charAt(j);
                        if(Character.isLetter(tmpChar)) {
                            switch(tmpChar) {
                                case 'A':
                                    codeBuild.append("10");
                                    break;
                                case 'B':
                                    codeBuild.append("11");
                                    break;
                                case 'C':
                                    codeBuild.append("12");
                                    break;
                                case 'D':
                                    codeBuild.append("13");
                                    break;
                                case 'E':
                                    codeBuild.append("14");
                                    break;
                                case 'F':
                                    codeBuild.append("15");
                                    break;
                                case 'G':
                                    codeBuild.append("16");
                                    break;
                                case 'H':
                                    codeBuild.append("17");
                                    break;
                                case 'I':
                                    codeBuild.append("18");
                                    break;
                                case 'J':
                                    codeBuild.append("19");
                                    break;
                                case 'K':
                                    codeBuild.append("20");
                                    break;
                                case 'L':
                                    codeBuild.append("21");
                                    break;
                                case 'M':
                                    codeBuild.append("22");
                                    break;
                                case 'N':
                                    codeBuild.append("23");
                                    break;
                                case 'O':
                                    codeBuild.append("24");
                                    break;
                                case 'P':
                                    codeBuild.append("25");
                                    break;
                                case 'Q':
                                    codeBuild.append("26");
                                    break;
                                case 'R':
                                    codeBuild.append("27");
                                    break;
                                case 'S':
                                    codeBuild.append("28");
                                    break;
                                case 'T':
                                    codeBuild.append("29");
                                    break;
                                case 'U':
                                    codeBuild.append("30");
                                    break;
                                case 'V':
                                    codeBuild.append("31");
                                    break;
                                case 'W':
                                    codeBuild.append("32");
                                    break;
                                case 'X':
                                    codeBuild.append("33");
                                    break;
                                case 'Y':
                                    codeBuild.append("34");
                                    break;
                                case 'Z':
                                    codeBuild.append("35");
                                    break;
                            }
                        } else codeBuild.append(tmpChar);
                    }
                    code[i] = codeBuild.toString();
                }
                String digits = code[3] + code[1] + code[2];
                int ib = Integer.parseInt(digits);
                if (ib != 1) return false;
                else return true;
            }
        } else return false;
    }
}
