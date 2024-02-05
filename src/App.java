import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import function.ExtractParentheses;
import function.Pair;

import table.ColumnDefinition;
import table.Row;
import table.TableDefinition;
import test.TestRhino;
import treatement.TableGenerator;
import treatement.CartesianProduct;
import treatement.CreateTable;
import treatement.Division;
import treatement.FromParenthesis;
import treatement.InsertRow;
import treatement.NaturalJoin;

public class App {
    static String sqlShow = "#SQL => ";
    static String sqlS = "#S => ";
    static String sqlC = "#C => ";
    static String sqlI = "#I => ";
    static String sqlP = "#P => ";
    static String sqlJ = "#J => ";
    static String sqlD = "#D => ";
    static boolean shouldExit = false;
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        System.out.println("Choissisiez parmi les lettres suivantes pour pouvoir continuer :");
        System.out.println("    - 'C' pour la création d'une nouvelle table");
        System.out.println("    - 'I' pour pouvoir insérer des valeur dans un table");
        System.out.println("    - 'J' pour faire la jointure naturelle de deux (2) tables");
        System.out.println("    - 'P' pour faire le produit cartésien de deux (2) tables");
        System.out.println("    - 'D pour faire la division entre deux (2) tables");
        System.out.println("    - 'S' pour avoir un aperçu d'une table");
        System.out.println("    - 'Q' pour quitter");

        while (true) {
            System.out.print(sqlShow);
        
            String request = scanner.nextLine();

            String tableName = null;

            switch (request.toUpperCase()) {
            case "C":
                while (true) {
                    boolean shouldExit7 = false;

                    System.out.print(sqlC + " Veuillez entrer le nom de la table : ");
                    tableName = scanner.nextLine();
                    tableName.toLowerCase();
                    
                    if (tableName.toUpperCase().equals("Q")) {
                        System.out.println("Voulez-vous réellement quitter? [O/N]");
                        System.out.print(sqlC);
                        String check = scanner.nextLine();
                        check.toUpperCase();
    
                        if (check.toUpperCase().equals("O")) {
                            shouldExit7 = true;
                        } else if (check.toUpperCase().equals("N")) {
                            shouldExit7 = false;
                        } else {
                            shouldExit7 = true;
                        }
                    } else {
                        new CreateTable(tableName);
    
                        System.out.println("Si vous voulez une auto-incrémentation, mettez 'increment' comme type");
                        System.out.println("Si vous voulez une chaine de caractères, mettez 'string' comme type");
                        System.out.println("Si vous voulez une chaine numérique, mettez 'integer' comme type");
                        System.out.println("Syntaxe : NomVariable TypeVariable");
    
                        System.out.print(sqlC + "Veuillez insérer le nom de la variable ainsi que son type : ");
    
                        String valueType = scanner.nextLine();
    
                        if (valueType.toUpperCase().equals("Q")) {
                            System.out.println("Voulez-vous réellement quitter? [O/N]");
                            System.out.print(sqlC);
                            String check = scanner.nextLine();
                            check.toUpperCase();
    
                            if (check.equals("O")) {
                                shouldExit = true;
                            } else if (check.equals("N")) {
                                shouldExit = false;
                            } else {
                                shouldExit = true;
                            }
    
                        } else {
                            String[] parts = valueType.split(" ");

                            if (parts.length < 2) {
                                System.out.println("Une erreur de syntaxe est survenue, il manque le type de la variable");
                            } else if (parts.length > 2) {
                                System.out.println("Une errreur de syntaxe est survenue, veuillez corrigé la syntaxe");
                            } else {
                                CreateTable.createTable(parts[0], parts[1]);
                                
                                while (true) {
                                    System.out.println("Si vous voulez quitter le paramétrage de la table, veuillez insérer 'E'");
                                    boolean shouldExit2 = false;
                                    System.out.print(sqlC + "Veuillez insérer un nouveau nom de variable ainsi que son type : ");
                                    String valueType2 = scanner.nextLine();
    
                                    if (valueType2.equals("E")) {
                                        shouldExit2 = true;
                                    } else {
                                        String[] part = valueType2.split(" "); 
                                        if (part.length > 2) {
                                            shouldExit2 = true;
                                        } else {
                                            CreateTable.insertColumnValueType(part[0], part[1]);
                                        }
                                    }
    
                                    if (shouldExit2) {
                                        break;
                                    }
                                }
                            }
                        }
                    }

                    if (shouldExit7) {
                        break;
                    }
                }

                break;

            case "I":
                boolean shouldExit2 = false;
                while (true) {
                    System.out.print(sqlI + "Veuillez indiquer la table dont laquelle vous voulez insérées des valeurs : ");
                    tableName = scanner.nextLine();

                    new TableDefinition(tableName.toLowerCase());
                    new Row(tableName.toLowerCase());
                    
                    if (tableName.toUpperCase().equals("Q")) {
                        System.out.println("Voulez-vous réellement quitter? [O/N]");
                        System.out.print(sqlI);
                        String check = scanner.nextLine();
    
                        if (check.toUpperCase().equals("O")) {
                            shouldExit2 = true;
                        } else if (check.toUpperCase().equals("N")) {
                            shouldExit2 = false;
                        } else {
                            shouldExit2 = true;
                        }
                    } else if (TableDefinition.getTableExist()){
                        boolean shouldExit3 = false;
    
                        while (true) {
                            System.out.println(sqlI + "Veuillez insérer des valeurs en ce format : " + TableDefinition.getColumnType());
    
                            System.out.print(sqlI);
                            String newRow = scanner.nextLine();
                            newRow.toLowerCase();
    
                            if (newRow.toUpperCase().equals("Q")) {
                                System.out.println("Voulez-vous réellement quitter? [O/N]");
                                System.out.print(sqlI);
                                String check = scanner.nextLine();
    
                                if (check.toUpperCase().equals("O")) {
                                    shouldExit3 = true;
                                    shouldExit2 = true;
                                } else if (check.toUpperCase().equals("N")) {
                                    shouldExit3 = false;
                                } else {
                                    shouldExit3 = true;
                                    shouldExit2 = true;
                                }
                            } else if (newRow.toUpperCase().equals("H")) {
                                for (String valueType : ColumnDefinition.getValueTypeList()) {
                                    if (TableDefinition.getColumnType().contains(valueType)) {
                                        System.out.println(TableDefinition.getColumn(valueType));
                                    }
                                }
                            } else if (newRow.startsWith("(") && newRow.endsWith(")")) {
                                new InsertRow(tableName.toLowerCase());
                                
                                String[] splitValues = null;
    
                                int valueAffected = 0;
    
                                String newRowWithoutSpace = newRow.replace(" ", "");
    
                                if (newRowWithoutSpace.contains("),")) {
                                    splitValues = newRowWithoutSpace.split("\\),");
                                    for (String value : splitValues) {
                                        if (!value.endsWith(")")) {
                                            value += ")";
                                        }
    
                                        if (InsertRow.insertRow(value)) {
                                            valueAffected++;
                                        }
                                    }
    
                                    System.out.println(valueAffected + " lignes de données ont été insérées avec succés");
                                } else {
                                    if (InsertRow.insertRow(newRowWithoutSpace)) {
                                        System.out.println("Les données sont insérées avec succés");
                                    }
                                }
    
                            } else {
                                System.out.println("Une erreur est survenue, veuillez verifier votre syntaxe");
                            }
    
                            if (shouldExit3) {
                                break;
                            }
                        }
                    } else {
                        System.out.println("La relation " + tableName + " n'existe pas");
                    }

                    if (shouldExit2) {
                        break;
                    }
                }

                break;

            case "S" :
                boolean shouldExit5 = false;

                while (true) {
                    System.out.print(sqlS + "Veuillez indiquer quelle table voulez-vous montrer : ");
                    tableName = scanner.nextLine();
                    tableName.toLowerCase();
    
                    new TableDefinition(tableName);
    
                    if (tableName.toUpperCase().equals("Q")) {
                        System.out.println("Voulez-vous réellement quitter? [O/N]");
                        System.out.print(sqlS);
                        String check = scanner.nextLine();
                        System.out.println(check);
    
                        if (check.toUpperCase().equals("O")) {
                            shouldExit5 = true;
                        } else if (check.toUpperCase().equals("N")) {
                            shouldExit5 = false;
                        } else {
                            shouldExit5 = true;
                        }
                    } else if (TableDefinition.getTableExist()) {
                        System.out.println("Veuillez choisir l'action à suivre");
                        System.out.println("    - T pour afficher toute la table");
                        System.out.println("    - S pour choisir les colonnes à afficher");
                        System.out.println("    - C pour choisir des conditions à afficher");

                        boolean shouldExit6 = false;

                        while (true) {
                            System.out.print(sqlS);
                            String action = scanner.nextLine();
        
                            new Row(tableName);
        
                            if (action.toUpperCase().equals("T")) {
                                System.out.println(TableGenerator.generateTable(TableDefinition.getHeaderList(), Row.getRow()));
                                int i = Row.getNumberofRows();
        
                                if (i < 1) {
                                    System.out.println(i + " élément selectionnné");
                                } else {
                                    System.out.println(i + " éléments sélectionnés");
                                }

                                shouldExit5 = true;
                                
                                break;
                            } else if (action.toUpperCase().equals("S")) {
                                System.out.println("Veuillez choisir l'action à suivre");
                                System.out.println("    - S pour choisir les colonnes à afficher sans conditions");
                                System.out.println("    - C pour choisir les colonnes à afficher avec conditions");
        
                                boolean shouldExit4 = false;
                                
                                while (true) {
                                    System.out.print(sqlS);
                                    String actionS = scanner.nextLine();
        
                                    if (actionS.toUpperCase().equals("S")) {
                                        System.out.print("#Projection => " + "Veuillez insérer le(s) nom(s) de(s) la colonne(s) que vous voulez afficher : ");
                                        String columns = scanner.nextLine();
        
                                        if (columns.startsWith("(") && columns.endsWith(")")) {
                                            List<String> headerList = new ArrayList<>();
                                            List<String> missingList = new ArrayList<>();
        
                                            for (String column : TableDefinition.getStringArray(columns)) {
                                                for (String header : TableDefinition.getHeaderList()) {
                                                    if (column.equals(header)) {
                                                        headerList.add(column);
                                                    }
                                                }
                                            }
        
                                            for (String column : TableDefinition.getStringArray(columns)) {
                                                if (!headerList.contains(column)) {
                                                    missingList.add(column);
                                                }
                                            }
        
                                            if (missingList.isEmpty()) {
                                                System.out.println(TableGenerator.generateTable(headerList, Row.getRowWithSpecificColumns(TableDefinition.getStringArray(columns))));
                                            } else {
                                                for (String missingColumn : missingList) {
                                                    System.out.println(missingColumn + " n'est pas une colonne de " + tableName);
                                                }
                                            }

                                            shouldExit5 = true;
                                            shouldExit6 = true;
                                            shouldExit4 = true;

                                        } else if (columns.equals("I")) {
                                            System.out.println("Voici les noms des colonnes de la table : " + tableName);
                                            for (String column : TableDefinition.getHeaderList()) {
                                                System.out.println("    - " + column);
                                            }
                                        } else if (columns.toUpperCase().equals("Q")) {
                                            System.out.println("Voulez-vous réellement quitter? [O/N]");
                                            System.out.print(sqlS);
                                            String check = scanner.nextLine();
    
                                            if (check.toUpperCase().equals("O")) {
                                                shouldExit5 = true;
                                                shouldExit6 = true;
                                                shouldExit4 = true;
                                            } else if (check.toUpperCase().equals("N")) {
                                                shouldExit4 = false;
                                            } else {
                                                shouldExit5 = true;
                                                shouldExit6 = true;
                                                shouldExit4 = true;
                                            }
                                        } else {
                                            System.out.println(columns + " est un requête inconnu");
                                        }
        
                                    } else if (actionS.toUpperCase().equals("C")) {
                                        System.out.print("#Pojection & Predicat => Veuillez insérer le(s) nom(s) de(s) la colonne(s) que vous voulez afficher : ");
                                        String projection = scanner.nextLine();
                                        projection.toLowerCase();
        
                                        if (projection.startsWith("(") && projection.endsWith(")")) {
                                            List<String> headerList = new ArrayList<>();
                                            List<String> missingList = new ArrayList<>();
                                            List<Pair<String,String>> conditionList = new ArrayList<>();
        
                                            for (String column : TableDefinition.getStringArray(projection)) {
                                                for (String header : TableDefinition.getHeaderList()) {
                                                    if (column.equals(header)) {
                                                        headerList.add(column);
                                                    }
                                                }
        
                                                if (!headerList.contains(column)) {
                                                    missingList.add(column);
                                                }
                                            }
        
                                            if (missingList.isEmpty()) {
                                                System.out.print("#Projection & Predicat => Veuillez insérer les conditions : ");
                                                String predicat = scanner.nextLine();
                                                predicat.toLowerCase();
        
                                                if (predicat.startsWith("(") && predicat.endsWith(")")) {
                                                    String[] splitConditions = null;
        
                                                    String newRowWithoutSpace = predicat.replace(" ", "");
        
                                                    if (newRowWithoutSpace.contains("),")) {
                                                        splitConditions = newRowWithoutSpace.split("\\),");
        
                                                        for (String condition : splitConditions) {
                                                            if (!condition.endsWith(")")) {
                                                                condition += ")";
                                                            }
        
                                                            conditionList.add(Pair.getPair(condition));
                                                        }
                                                    } else {
                                                        conditionList.add(Pair.getPair(predicat));
                                                    }
                                                }
                                            }
        
                                            if (projection.startsWith("(") && projection.endsWith(")")) {
                                                System.out.println(TableGenerator.generateTable(headerList, Row.getRowWithSpecificColumnsWithCondition(conditionList, TableDefinition.getStringArray(projection))));
                                            }
        
                                            shouldExit5 = true;
                                            shouldExit6 = true;
                                            shouldExit4 = true;

                                        }
        
                                    } else if (actionS.toUpperCase().equals("I")) {
                                        System.out.println("Voici les noms des colonnes de la table : " + tableName);
                                        for (String column : TableDefinition.getHeaderList()) {
                                            System.out.println("    - " + column);
                                        }
                                    } else if (actionS.toUpperCase().equals("Q")) {
                                        System.out.println("Voulez-vous réellement quitter? [O/N]");
                                        System.out.print(sqlS);
                                        String check = scanner.nextLine();
    
                                        if (check.toUpperCase().equals("O")) {
                                            shouldExit5 = true;
                                            shouldExit6 = true;
                                            shouldExit4 = true;
                                        } else if (check.toUpperCase().equals("N")) {
                                            shouldExit4 = false;
                                        } else {
                                            shouldExit5 = true;
                                            shouldExit6 = true;
                                            shouldExit4 = true;
                                        }

                                    } else {
                                        System.out.println(actionS + " est une requête inconnue");
                                    }
        
                                    if (shouldExit4) {
                                        break;
                                    }
                                }
        
                            } else if (action.toUpperCase().equals("C")) {
                                System.out.print("#Predicat => Veuillez choisir vos conditions : ");
                                String condition = scanner.nextLine();
                                condition.toLowerCase();
        
                                List<Pair<String,String>> conditionList = new ArrayList<>();
        
                                if (condition.startsWith("[") && condition.endsWith("]")) {
                                    String[] splitConditions = null;
        
                                    String newRowWithoutSpace = condition.replace(" ", "");

                                    if (newRowWithoutSpace.contains("],")) {
                                        splitConditions = newRowWithoutSpace.split("\\],");

                                        for (String splitString : splitConditions) {
                                            if (splitString.contains("(") && splitString.contains(")")) {
                                                String operand = ExtractParentheses.extractParentheses(splitString);
                                                double result = TestRhino.evaluate(operand);
                                                if (ExtractParentheses.isInteger(result)) {
                                                    splitString = splitString.replace(operand, String.valueOf((int)result));
                                                    splitString = splitString.replace("(", "").replace(")", "");
                                                } else {
                                                    splitString = splitString.replace(operand, String.valueOf(result));
                                                    splitString = splitString.replace("(", "").replace(")", "");
                                                    System.out.println(splitString);
                                                }
                                            }
                                            if (!splitString.endsWith("]")) {
                                                splitString += "]";
                                            }
        
                                            conditionList.add(Pair.getPair(splitString));
                                        }
                                    } else {
                                        if (newRowWithoutSpace.contains("(") && newRowWithoutSpace.contains(")")) {
                                            String operand = ExtractParentheses.extractParentheses(newRowWithoutSpace);
                                            double result = TestRhino.evaluate(operand);
                                            if (ExtractParentheses.isInteger(result)) {
                                                newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                            } else {
                                                newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                            }
                                            newRowWithoutSpace = newRowWithoutSpace.replace("(", "").replace(")", "");
                                            if (ExtractParentheses.isInteger(result)) {
                                                newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                            } else {
                                                newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                            }
                                        }
                                        conditionList.add(Pair.getPair(newRowWithoutSpace));
                                    }
                                }
        
                                System.out.println(TableGenerator.generateTable(TableDefinition.getHeaderList(), Row.getRowWithCondition(conditionList)));
                            } else if (action.toUpperCase().equals("Q")) {
                                System.out.println("Voulez-vous réellement quitter? [O/N]");
                                System.out.print(sqlS);
                                String check = scanner.nextLine();
    
                                if (check.toUpperCase().equals("O")) {
                                    shouldExit6 = true;
                                    shouldExit5 = true;
                                } else if (check.toUpperCase().equals("N")) {
                                    shouldExit6 = false;
                                } else {
                                    shouldExit6 = true;
                                    shouldExit5 = true;
                                }
                            } else {
                                System.out.println(action + " est une requête inconnue");    
                            }

                            if (shouldExit6) {
                                break;
                            }
                        }
    
                    } else if (tableName.startsWith("(") && tableName.endsWith(")")) {
                        String[] fromParenthese = tableName.substring(1, tableName.length() - 1).split(",");

                        if (fromParenthese.length == 2) {
                            if (fromParenthese[0].contains("*")) {
                                String[] cartesianTable = fromParenthese[0].split("\\*");
                                
                                if (cartesianTable[0].equals(cartesianTable[1])) {
                                    System.out.println("Les deux tables possède le même nom donc il n'est pas possible d'effectuer le produit cartésien");
                                } else if (!(TableDefinition.exist(cartesianTable[0]) && TableDefinition.exist(cartesianTable[1])) || !(TableDefinition.exist(cartesianTable[0]) || TableDefinition.exist(cartesianTable[1]))) {
                                    if (!TableDefinition.exist(cartesianTable[0]) && !TableDefinition.exist(cartesianTable[1])) {
                                        System.out.println("Ni la relation " + cartesianTable[0] + " ni la relation " + cartesianTable[1] + " n'existent");
                                    } else if (!TableDefinition.exist(cartesianTable[0])) {
                                        System.out.println("La relation " + cartesianTable[0] + " n'existe pas");
                                    } else if (!TableDefinition.exist(cartesianTable[1])) {
                                        System.out.println("La relation " + cartesianTable[1] + " n'existe pas");
                                    }
                                } else {
                                    String cartesianTable4 = FromParenthesis.cartesianTable(cartesianTable, fromParenthese[1]);
        
                                    new TableDefinition(cartesianTable4);

                                    System.out.print("#Predicat => Veuillez choisir vos conditions : ");
                                    String condition = scanner.nextLine();
                                    condition.toLowerCase();
                                    
                                    List<Pair<String,String>> conditionList = new ArrayList<>();
                                    
                                    if (condition.startsWith("[") && condition.endsWith("]")) {
                                        String[] splitConditions = null;
                                    
                                        String newRowWithoutSpace = condition.replace(" ", "");

                                        if (newRowWithoutSpace.contains("],")) {
                                            splitConditions = newRowWithoutSpace.split("\\],");

                                            for (String splitString : splitConditions) {
                                                if (splitString.contains("(") && splitString.contains(")")) {
                                                    String operand = ExtractParentheses.extractParentheses(splitString);
                                                    double result = TestRhino.evaluate(operand);
                                                    if (ExtractParentheses.isInteger(result)) {
                                                        splitString = splitString.replace(operand, String.valueOf((int)result));
                                                        splitString = splitString.replace("(", "").replace(")", "");
                                                    } else {
                                                        splitString = splitString.replace(operand, String.valueOf(result));
                                                        splitString = splitString.replace("(", "").replace(")", "");
                                                        System.out.println(splitString);
                                                    }
                                                }
                                                if (!splitString.endsWith("]")) {
                                                    splitString += "]";
                                                }
                                            
                                                conditionList.add(Pair.getPair(splitString));
                                            }
                                        } else {
                                            if (newRowWithoutSpace.contains("(") && newRowWithoutSpace.contains(")")) {
                                                String operand = ExtractParentheses.extractParentheses(newRowWithoutSpace);
                                                double result = TestRhino.evaluate(operand);
                                                if (ExtractParentheses.isInteger(result)) {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                                } else {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                                }
                                                newRowWithoutSpace = newRowWithoutSpace.replace("(", "").replace(")", "");
                                                if (ExtractParentheses.isInteger(result)) {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                                } else {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                                }
                                            }
                                            conditionList.add(Pair.getPair(newRowWithoutSpace));
                                        }
                                    }

                                    new Row(cartesianTable4);
                                
                                    System.out.println(TableGenerator.generateTable(TableDefinition.getHeaderList(), Row.getRowWithCondition(conditionList)));

                                    int i = Row.getRowWithCondition(conditionList).size();
        
                                    if (i < 1) {
                                        System.out.println(i + " élément selectionnné");
                                    } else {
                                        System.out.println(i + " éléments sélectionnés");
                                    }

                                    CreateTable createTable = new CreateTable(cartesianTable4);
                                    createTable.suppressTempTable();

                                    shouldExit5 = true;
                                }
                            } else if (fromParenthese[0].contains(">")) {
                                String[] joinTable = fromParenthese[0].split("\\>");

                                if (joinTable[0].equals(joinTable[1])) {
                                    System.out.println("Les deux tables possède le même nom donc il n'est pas possible d'effectuer le produit cartésien");
                                } else if (!(TableDefinition.exist(joinTable[0]) && TableDefinition.exist(joinTable[1])) || !(TableDefinition.exist(joinTable[0]) || TableDefinition.exist(joinTable[1]))) {
                                    if (!TableDefinition.exist(joinTable[0]) && !TableDefinition.exist(joinTable[1])) {
                                        System.out.println("Ni la relation " + joinTable[0] + " ni la relation " + joinTable[1] + " n'existent");
                                    } else if (!TableDefinition.exist(joinTable[0])) {
                                        System.out.println("La relation " + joinTable[0] + " n'existe pas");
                                    } else if (!TableDefinition.exist(joinTable[1])) {
                                        System.out.println("La relation " + joinTable[1] + " n'existe pas");
                                    }
                                } else {
                                    String joinTable4 = FromParenthesis.joinTable(joinTable, fromParenthese[1]);

                                    new TableDefinition(joinTable4);

                                    System.out.print("#Predicat => Veuillez choisir vos conditions : ");
                                    String condition = scanner.nextLine();
                                    condition.toLowerCase();
                                    
                                    List<Pair<String,String>> conditionList = new ArrayList<>();
                                    
                                    if (condition.startsWith("[") && condition.endsWith("]")) {
                                        String[] splitConditions = null;
                                    
                                        String newRowWithoutSpace = condition.replace(" ", "");

                                        if (newRowWithoutSpace.contains("],")) {
                                            splitConditions = newRowWithoutSpace.split("\\],");

                                            for (String splitString : splitConditions) {
                                                if (splitString.contains("(") && splitString.contains(")")) {
                                                    String operand = ExtractParentheses.extractParentheses(splitString);
                                                    double result = TestRhino.evaluate(operand);
                                                    if (ExtractParentheses.isInteger(result)) {
                                                        splitString = splitString.replace(operand, String.valueOf((int)result));
                                                        splitString = splitString.replace("(", "").replace(")", "");
                                                    } else {
                                                        splitString = splitString.replace(operand, String.valueOf(result));
                                                        splitString = splitString.replace("(", "").replace(")", "");
                                                        System.out.println(splitString);
                                                    }
                                                }
                                                if (!splitString.endsWith("]")) {
                                                    splitString += "]";
                                                }
                                            
                                                conditionList.add(Pair.getPair(splitString));
                                            }
                                        } else {
                                            if (newRowWithoutSpace.contains("(") && newRowWithoutSpace.contains(")")) {
                                                String operand = ExtractParentheses.extractParentheses(newRowWithoutSpace);
                                                double result = TestRhino.evaluate(operand);
                                                if (ExtractParentheses.isInteger(result)) {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                                } else {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                                }
                                                newRowWithoutSpace = newRowWithoutSpace.replace("(", "").replace(")", "");
                                                if (ExtractParentheses.isInteger(result)) {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf((int)result));
                                                } else {
                                                    newRowWithoutSpace = newRowWithoutSpace.replace(operand, String.valueOf(result));
                                                }
                                            }
                                            conditionList.add(Pair.getPair(newRowWithoutSpace));
                                        }
                                    }

                                    new Row(joinTable4);
                                
                                    System.out.println(TableGenerator.generateTable(TableDefinition.getHeaderList(), Row.getRowWithCondition(conditionList)));

                                    int i = Row.getRowWithCondition(conditionList).size();
        
                                    if (i < 1) {
                                        System.out.println(i + " élément selectionnné");
                                    } else {
                                        System.out.println(i + " éléments sélectionnés");
                                    }

                                    CreateTable createTable = new CreateTable(joinTable4);
                                    createTable.suppressTempTable();
        
                                    shouldExit5 = true;
                                }

                            }
                        } else if (fromParenthese.length > 2) {
                            System.out.println("Une erreur de syntaxe est survenue. Il y a " + fromParenthese.length + " arguments au lieu de 2 arguments.");
                        } else if (fromParenthese.length < 2) {
                            System.out.println("Une erreur de syntaxe est survenue. Il manque " + (2 - fromParenthese.length) + " argument.");
                        } else {
                            System.out.println("Une erreur de syntaxe est survenue, veuillez recommencer");
                        }
                    } else {
                        System.out.println("La relation \"" + tableName + "\" n'existe pas");
                    }

                    if (shouldExit5) {
                        break;
                    }
                }

                break;

            case "P" :
                boolean shouldExit4 = false;

                while (true) {
                    System.out.println(sqlP + "Veuillez indiquer les relations dont vous voulez effectuez le produit cartésien");
                    
                    while (true) {
                        System.out.print(sqlP);
                        String action = scanner.nextLine();
                        action = action.replace(" ", "");
                        action = action.toLowerCase();
                        boolean shouldExit7 = false;

                        if (action.toUpperCase().equals("F")) {
                            System.out.println("Veuillez insérer les noms des relations en cette forme : |nomtable1| * |nomtable2|");
                            shouldExit7 = true;
                        } else if (action.toUpperCase().equals("Q")) {
                            System.out.println("Voulez-vous réellement quitter ? [O/N]");
                            System.out.print(sqlP);
                            String exit = scanner.nextLine();
                            exit = exit.toUpperCase();

                            if (exit.equals("O")) {
                                shouldExit7 = true;
                                shouldExit4 = true;
                            } else if (exit.equals("N")) {
                                shouldExit7 = false;
                            } else {
                                shouldExit7 = true;
                                shouldExit4 = true;
                            }
                        } else if (action.startsWith("|") && action.endsWith("|")) {
                            String[] tables = action.substring(1, action.length() - 1).split("\\|\\*\\|");

                            if (tables[0].equals(tables[1])) {
                                System.out.println("Les deux tables possède le même nom donc il n'est pas possible d'effectuer le produit cartésien");
                            } else if (!(TableDefinition.exist(tables[0]) && TableDefinition.exist(tables[1])) || !(TableDefinition.exist(tables[0]) || TableDefinition.exist(tables[1]))) {
                                if (!TableDefinition.exist(tables[0]) && !TableDefinition.exist(tables[1])) {
                                    System.out.println("Ni la relation " + tables[0] + " ni la relation " + tables[1] + " n'existent");
                                } else if (!TableDefinition.exist(tables[0])) {
                                    System.out.println("La relation " + tables[0] + " n'existe pas");
                                } else if (!TableDefinition.exist(tables[1])) {
                                    System.out.println("La relation " + tables[1] + " n'existe pas");
                                }
                            } else {
                                new CartesianProduct(tables[0], tables[1]);
    
                                CartesianProduct.getTable();
    
                                shouldExit7 = true;
                                shouldExit4 = true;
                            }

                        } else {
                            System.out.println("La syntaxe est incorrecte, veuillez vérifier si elle correspond à la syntaxe demandée");
                            System.out.println("Insérer F pour voir le modèle de la syntaxe");
                            shouldExit7 = true;
                        }

                        if (shouldExit7) {
                            break;
                        }
                    }

                    if (shouldExit4) {
                        break;
                    }
                }

                break;

            case "J" :
                boolean shouldExit7 = false;

                while (true) {
                    System.out.println(sqlJ + "Veuillez insérer le nom des deux (2) tables à joindre :");
                    System.out.print(sqlJ);
                    String action = scanner.nextLine();
                    action = action.replace(" ", "");
                    action = action.toLowerCase();

                    if (action.toUpperCase().equals("Q")) {
                        System.out.println("Voulez-vous réellement quitter ? [O/N]");
                        System.out.print(sqlJ);
                        String exit = scanner.nextLine();
                        exit = exit.toUpperCase();

                        if (exit.equals("O")) {
                            shouldExit7 = true;
                        } else if (exit.equals("N")) {
                            shouldExit7 = false;
                        } else {
                            shouldExit7 = true;
                        }
                    } else if (action.startsWith("|") && action.endsWith("|")) {
                        String[] tables = action.substring(1, action.length() - 1).split("\\|\\>\\|");

                        if (tables[0].equals(tables[1])) {
                            System.out.println("Les noms des deux (2) tables sont identiques");
                        } else if (!(TableDefinition.exist(tables[0]) && TableDefinition.exist(tables[1])) || !(TableDefinition.exist(tables[0]) || TableDefinition.exist(tables[1]))) {
                            if (!TableDefinition.exist(tables[0]) && !TableDefinition.exist(tables[1])) {
                                    System.out.println("Ni la relation " + tables[0] + " ni la relation " + tables[1] + " n'existent");
                            } else if (!TableDefinition.exist(tables[0])) {
                                System.out.println("La relation " + tables[0] + " n'existe pas");
                            } else if (!TableDefinition.exist(tables[1])) {
                                System.out.println("La relation " + tables[1] + " n'existe pas");
                            }
                        } else {
                            new NaturalJoin(tables[0], tables[1]);

                            NaturalJoin.getNaturalJoin();

                            shouldExit7 = true;
                        }
                    } else if (action.toUpperCase().equals("I")) {
                        System.out.println("Voici la syntaxe pour insérer le nom des deux (2) tables : |nomtable1|>|nomtable2|");
                    } else {
                        System.out.println("La syntaxe insérée n'est pas valide");
                        System.out.println("Veuillez insérer F pour voir la syntaxe à insérée");
                    }
                    
                    if (shouldExit7) {
                        break;
                    }
                }
                break;
            
            case "D":
                boolean shouldExit6 = false;

                while (true) {
                    System.out.println(sqlD + "Veuillez insérer le nom des deux tables dont vous voulez effectuer la division : ");
                    System.out.print(sqlD);
                    String action = scanner.nextLine();
                    action = action.replace(" ", "");
                    action = action.toLowerCase();

                    if (action.toUpperCase().equals("Q")) {
                        System.out.println("Voulez-vous réellement quitter ? [O/N]");
                        System.out.print(sqlJ);
                        String exit = scanner.nextLine();
                        exit = exit.toUpperCase();

                        if (exit.equals("O")) {
                            shouldExit6 = true;
                        } else if (exit.equals("N")) {
                            shouldExit6 = false;
                        } else {
                            shouldExit6 = true;
                        }
                    } else if (action.startsWith("|") && action.endsWith("|")) {
                        String[] tables = action.substring(1, action.length() - 1).split("\\|\\*\\|");

                        if (tables[0].equals(tables[1])) {
                            System.out.println("Les noms des deux (2) tables sont identiques.");
                        } else if (!(TableDefinition.exist(tables[0]) && TableDefinition.exist(tables[1])) || !(TableDefinition.exist(tables[0]) || TableDefinition.exist(tables[1]))) {
                            if (!TableDefinition.exist(tables[0]) && !TableDefinition.exist(tables[1])) {
                                    System.out.println("Ni la relation " + tables[0] + " ni la relation " + tables[1] + " n'existent");
                            } else if (!TableDefinition.exist(tables[0])) {
                                System.out.println("La relation " + tables[0] + " n'existe pas");
                            } else if (!TableDefinition.exist(tables[1])) {
                                System.out.println("La relation " + tables[1] + " n'existe pas");
                            }
                        } else {
                            new Division(tables[0], tables[1]).getTable();

                            shouldExit6 = true;
                        }
                    } else if (action.toUpperCase().equals("I")) {
                        System.out.println("Voici la syntaxe pour insérer le nom des deux (2) tables : |nomtable1|*|nomtable2|");
                    } else {
                        System.out.println("La syntaxe insérée n'est pas valide");
                        System.out.println("Veuillez insérer F pour voir la syntaxe à insérée");
                    }

                    if (shouldExit6) {
                        break;
                    }
                }

                break;
            
            case "Q" :
                System.out.println("Voulez-vous réellement quitter? [O/N]");
                System.out.print(sqlShow);
                String check = scanner.nextLine();
                check.toUpperCase();

                if (check.equals("O")) {
                    shouldExit = true;
                } else if (check.equals("N")) {
                    shouldExit = false;
                } else {
                    shouldExit = true;
                }

                break;
                
            default:
                System.out.println(request + " est une requête inconnue");
                break;
            }

            if (shouldExit) {
                break;
            }
        }

        scanner.close();
    }
}