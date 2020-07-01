import java.util.*;

public class Mastermind {
    static List<String> colorList = Arrays.asList("rouge", "jaune", "vert", "bleu", "orange", "blanc", "violet", "fuschia");
    static Scanner userInput = new Scanner(System.in);

    public static ArrayList<String> getWinningList() {
        List<String> randomList = new ArrayList<>(colorList);
        Collections.shuffle(randomList);
        return new ArrayList<>(randomList.subList(0, 4));
    }

    public static int[] checkColor(ArrayList<String> listToCheck, ArrayList<String> winningList) {
        int[] result = new int[2];
        for (String color: listToCheck) {
            if (winningList.contains(color) && listToCheck.indexOf(color) != winningList.indexOf(color)) {
                result[0] += 1;
            } else if (winningList.contains(color) && listToCheck.indexOf(color) == winningList.indexOf(color)) {
                result[1] += 1;
            }
        }
        return result;
    }

    public static void displayResult(int[] result) {
        System.out.println("***********************************************************");
        System.out.println("**    Nombre de couleurs bien placées : " +result[1] +"                **");
        System.out.println("**    Nombre de bonnes couleurs, mais mal placées : " +result[0]+"    **");
        System.out.println("***********************************************************");
    }

    public static void displayColorList(List<String> listToDisplay) {
        System.out.println(" ");
        for (String color : listToDisplay) {
            System.out.print(listToDisplay.indexOf(color)+1 +" > " + color + "        ");
        }
        System.out.println("\n ");
    }
    public static  Boolean checkUserList(ArrayList<String> colorsChosenByUser) {
        int chosenNumber;
        while (userInput.hasNext()) {
            if (userInput.hasNextInt()) {
                chosenNumber = userInput.nextInt() - 1;
                if (chosenNumber >= 0 && chosenNumber <= 7 && !colorsChosenByUser.contains(colorList.get(chosenNumber))) {
                    colorsChosenByUser.add(colorList.get(chosenNumber));
                    return true;
                } else {
                    System.out.println("Cette couleur n'est pas disponible, recommencez.");
                    return false;
                }
            } else {
                System.out.println("Votre saise est invalide, recommencez.");
                userInput.next();
                return false;
            }
        }
        return false;
    }

    public static  ArrayList<String> userList() {
        ArrayList<String> colorsChosenByUser = new ArrayList<>();
        int remainingColors = 1;
        while (remainingColors <= 4) {
            System.out.println(remainingColors + "/4 - Veuillez entrer le numéro de la couleur désirée : ");
            displayColorList(colorList);
            if (checkUserList(colorsChosenByUser)) {
                System.out.println("Voici les couleurs que vous avez choisies : ");
                displayColorList(colorsChosenByUser);
                remainingColors++;
            }
        }
        return colorsChosenByUser;
    }

    public static void main(String[] args){
        int gameRounds = 1;
        ArrayList<String> game = getWinningList();
        System.out.println("Bienvenu dans le Mastermind.");
        System.out.println("Etes-vous prêt à relever le défi ? C'est parti ! \n " +
                "Vous devez choisir quatre couleurs parmi la liste suivant : \n" +
                "Attention, vous ne devez choisir qu'une seule fois la même couleur dans cette liste :");
        while (gameRounds <= 12) {
            ArrayList<String> answer = userList();
            int[] result = checkColor(answer, game);
            displayResult(result);
            if (result[1] == 4) {
                System.out.println("Vous avez gagné !");
                return;
            }
            gameRounds += 1;
        }
        System.out.println("Vous avez perdu.");
        userInput.close();
    }

   /* public static void afficherJeuGagnant(ArrayList<String> aAfficher) {
        for (String s : aAfficher) {
            System.out.println(s);
        }
    }*/
}
