import java.util.*;

class Main {
    static List<String> cards = new ArrayList<>();
    static List<String> deckCards = new ArrayList<>();
    static List<String> playerOneHand = new ArrayList<>();
    static List<String> playerTwoHand = new ArrayList<>();
    static List<String> playerOneHandView = new ArrayList<>();
    static List<String> playerTwoHandView = new ArrayList<>();
    static int playerOneWins = 0;
    static int playerTwoWins = 0;
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        cards.addAll(Arrays.asList("A","A","A","A","2","2","2","2","3","3","3","3",
            "4","4","4","4","5","5","5","5","6","6","6","6","7","7","7","7",
            "8","8","8","8","9","9","9","9","10","10","10","10","J","J","J","J",
            "Q","Q","Q","Q","K","K","K","K"));
        playGame();

    }

    public static void playGame() {
        int cardInHand = 0;
        while(playerOneWins < 10 && playerTwoWins < 10) {
            String kingPlacement = "";
            int thisIndex = -1;
            String deckCard = "";
            boolean isPlayerOneTurn = true;
            deckCards = new ArrayList<>(cards);
            clearView();
            dealHand("p1");
            dealHand("");
            boolean isRoundOver=false;
            boolean isValidInteger = false;
            boolean isBeginningOfTurn = true;
            while(!isRoundOver) {

                if (isPlayerOneTurn) {
                    if (isBeginningOfTurn) {
                        System.out.println("-------------Player One Turn-------------");
                        isBeginningOfTurn = false;
                    }
                    if(isValidInteger && !deckCard.equals("") &&
                        Integer.parseInt(deckCard) <= playerOneHand.size()
                       && playerOneHandView.get(Integer.parseInt(deckCard) - 1).equals("X")) {

                    } else if (deckCard.equals("A") &&
                        playerOneHandView.get(0).equals("X")) {

                    } else {
                        thisIndex =random.nextInt(deckCards.size());
                        deckCard=deckCards.get(thisIndex);
                    }
                    try
                    {
                       cardInHand = Integer.parseInt(deckCard);
                       isValidInteger = true;
                    }
                    catch (NumberFormatException ex) {
                        isValidInteger = false;
                    }

                    boolean isTurnOver = false;
                    String tempCard = "";
                    while(!isTurnOver) {
                        if(isRoundOver) {
                            break;
                        }
                        printHand("Player One");
                        System.out.println("Deck Card: " + deckCard);

                        if ((isValidInteger && Integer.parseInt(deckCard) <= playerOneHand.size())
                            || deckCard.equals("K") || deckCard.equals("A")) {
                            if(deckCard.equals("A") && playerOneHandView.get(0).equals("X")) {
                                cardInHand=1;
                                tempCard = playerOneHand.get(cardInHand-1);
                                playerOneHandView.set(cardInHand-1, "A");
                                deckCard = tempCard;
                                System.out.println("Place your card!");
                                scanner.nextLine();
                                if(checkIfWon(playerOneHandView)) {
                                    System.out.println("!!!!!!!!!!!!!PLAYER ONE!!!!!!!!!!!!!");
                                    playerOneWins++;
                                    isRoundOver = true;
                                    break;
                                }
                            }
                            if(deckCard.equals("K")) {
                                boolean isPlaced = false;
                                while (!isPlaced) {
                                    System.out.println("Where would like the king to go?");
                                    kingPlacement = scanner.nextLine();
                                    cardInHand = Integer.parseInt(kingPlacement);
                                    if(cardInHand <= playerTwoHand.size() &&
                                        playerOneHandView.get(cardInHand-1).equals("X")) {
                                        tempCard = playerOneHand.get(cardInHand-1);
                                        playerOneHandView.set(cardInHand-1,
                                            "K");
                                        deckCard = tempCard;
                                        if(checkIfWon(playerOneHandView)) {
                                            System.out.println("!!!!!!!!!!!!!PLAYER ONE!!!!!!!!!!!!!");
                                            playerOneWins++;
                                            isRoundOver = true;
                                            break;
                                        }
                                        isPlaced =true;
                                    }
                                }

                            }
                            if((cardInHand <= playerOneHand.size() &&
                                playerOneHandView.get(cardInHand-1).equals("X")) &&
                                !deckCard.equals("K")  && !deckCard.equals("A")) {

                                tempCard = playerOneHand.get(cardInHand-1);
                                playerOneHandView.set(cardInHand-1,
                                    new Integer(cardInHand).toString());
                                deckCard = tempCard;
                                System.out.println("Place your card!");
                                scanner.nextLine();
                                System.out.println("You placed a " + cardInHand);
                                if(checkIfWon(playerOneHandView)) {
                                    System.out.println("!!!!!!!!!!!!!PLAYER ONE!!!!!!!!!!!!!");
                                    playerOneWins++;
                                    isRoundOver = true;
                                    break;
                                }
                            }

                        }
                        if(isRoundOver) {
                            break;
                        }
                        try
                        {
                           cardInHand = Integer.parseInt(tempCard);
                           isValidInteger = true;
                        }
                        catch (NumberFormatException ex) {
                            isValidInteger = false;
                        }
                        if(!isValidInteger && !deckCard.equals("K") && !deckCard.equals("A")) {
                            isTurnOver = true;
                            isPlayerOneTurn = false;
                            isBeginningOfTurn = true;
                        } if(isValidInteger) {
                            if ((cardInHand <= playerOneHand.size() &&
                                !playerOneHandView.get(cardInHand - 1).equals("X")) ||
                                cardInHand > playerOneHand.size()) {
                                isTurnOver = true;
                                isPlayerOneTurn = false;
                                isBeginningOfTurn = true;
                            }
                        } if(deckCard.equals("A")) {
                            if(!playerOneHandView.get(0).equals("X")) {
                                isTurnOver = true;
                                isPlayerOneTurn = false;
                                isBeginningOfTurn = true;
                            }
                        }


                }

                } else {

                    if (isBeginningOfTurn) {
                        System.out.println("-------------Player Two Turn-------------");
                        isBeginningOfTurn = false;
                    }
                    if(isValidInteger &&
                        Integer.parseInt(deckCard) <= playerTwoHand.size() &&
                        playerTwoHandView.get(Integer.parseInt(deckCard)-1).equals("X")) {

                    } else if (deckCard.equals("A") &&
                        playerTwoHandView.get(0).equals("X")) {

                    } else if(deckCard.equals("K")) {

                    } else {
                        thisIndex =random.nextInt(deckCards.size());
                        deckCard=deckCards.get(thisIndex);
                    }
                    try
                    {
                       cardInHand = Integer.parseInt(deckCard);
                       isValidInteger = true;
                    }
                    catch (NumberFormatException ex) {
                        isValidInteger = false;
                    }

                    boolean isTurnOver = false;
                    String tempCard = "";
                    while(!isTurnOver) {
                        if (isRoundOver) {
                            break;
                        }
                        printHand("Player Two");
                        System.out.println("Deck Card: " + deckCard);

                        if ((isValidInteger && Integer.parseInt(deckCard) <= playerTwoHand.size() )
                            || deckCard.equals("K") || deckCard.equals("A")) {
                            if(deckCard.equals("A") && playerTwoHandView.get(0).equals("X")) {
                                cardInHand=1;
                                tempCard = playerTwoHand.get(cardInHand-1);
                                playerTwoHandView.set(cardInHand-1, "A");
                                deckCard = tempCard;
                                System.out.println("Place your card!");
                                scanner.next();
                                if(checkIfWon(playerTwoHandView)) {
                                    System.out.println("!!!!!!!!!!!!!PLAYER TWO!!!!!!!!!!!!!");
                                    playerTwoWins++;
                                    isRoundOver = true;
                                    break;
                                }
                            }
                            if(deckCard.equals("K")) {
                                boolean isPlaced = false;
                                while (!isPlaced) {
                                    System.out.println("Where would like the king to go?");
                                    kingPlacement = scanner.nextLine();
                                    cardInHand = Integer.parseInt(kingPlacement);
                                    if(cardInHand <= playerTwoHand.size() &&
                                        playerTwoHandView.get(cardInHand-1).equals("X")) {
                                        tempCard = playerTwoHand.get(cardInHand-1);
                                        playerTwoHandView.set(cardInHand-1,
                                            "K");
                                        deckCard = tempCard;
                                        if(checkIfWon(playerTwoHandView)) {
                                            System.out.println("!!!!!!!!!!!!!PLAYER TWO!!!!!!!!!!!!!");
                                            playerTwoWins++;
                                            isRoundOver = true;
                                            break;

                                        }
                                        isPlaced =true;
                                    }
                                }

                            }
                            if(cardInHand <= playerTwoHand.size() &&
                                playerTwoHandView.get(cardInHand-1).equals("X") &&
                                !deckCard.equals("K")  && !deckCard.equals("A")) {

                                tempCard = playerTwoHand.get(cardInHand-1);
                                playerTwoHandView.set(cardInHand-1,
                                    new Integer(cardInHand).toString());
                                deckCard = tempCard;
                                System.out.println("Place your card!");
                                scanner.nextLine();
                                System.out.println("You placed a " + cardInHand);
                                if(checkIfWon(playerTwoHandView)) {
                                    System.out.println("!!!!!!!!!!!!!PLAYER TWO!!!!!!!!!!!!!");
                                    playerTwoWins++;
                                    isRoundOver = true;
                                    break;
                                }
                            }

                        }
                        if(isRoundOver) {
                            break;
                        }

                        try
                        {
                           cardInHand = Integer.parseInt(tempCard);
                           isValidInteger = true;
                        }
                        catch (NumberFormatException ex) {
                            isValidInteger = false;
                        }
                        if(!isValidInteger && !deckCard.equals("K") && !deckCard.equals("A")) {
                            isTurnOver = true;
                            isPlayerOneTurn = true;
                            isBeginningOfTurn = true;
                        } if(isValidInteger) {
                            if ((cardInHand <= playerTwoHand.size() &&
                                !playerTwoHandView.get(cardInHand - 1).equals("X")) ||
                                cardInHand > playerTwoHand.size()) {
                                isTurnOver = true;
                                isPlayerOneTurn = true;
                                isBeginningOfTurn = true;
                            }
                        } if(deckCard.equals("A")) {
                            if(!playerTwoHandView.get(0).equals("X")) {
                                isTurnOver = true;
                                isPlayerOneTurn = true;
                                isBeginningOfTurn = true;
                            }
                        }


                }

                }



            }
        }
        String winner = "";
        String playAgain = "";
        if (playerOneWins == 10) {
            winner = "ONE";
        } else {
            winner = "TWO";
        }

            System.out.println("PLAYER " + winner +"!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!YOU WIN!!!!!!!!!!!!!!!");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!CRUSHED IT!!");
            System.out.println("Play again? (Y/N)");
            playAgain = scanner.nextLine();

            if (playAgain.toUpperCase().equals("Y")) {
                reset();
            }



    }

    private static void reset() {
        playerOneWins = 0;
        playerTwoWins = 0;
        playGame();
    }

    private static void printHand(String player) {
        System.out.print(player + ": ");
        if (player.equals("Player One")) {
            for (String card : playerOneHandView) {
                System.out.print(card + " ");
            }
            System.out.println();
        }
        else {
            for (String card : playerTwoHandView) {
                System.out.print(card + " ");
            }
            System.out.println();
        }
    }

    private static boolean checkIfWon(List<String> tempList) {
        if(!tempList.contains("X")) {
            return true;
        } else {
            return false;
        }
    }


    public static void clearView() {
        playerOneHand = new ArrayList<>();
        playerTwoHand = new ArrayList<>();
        playerOneHandView = new ArrayList<>();
        playerTwoHandView = new ArrayList<>();
        for(int i=0; i<10-playerOneWins;i++) {
            playerOneHandView.add("X");
        }
        for(int i=0; i<10-playerTwoWins;i++) {
            playerTwoHandView.add("X");
        }
    }

    public static void dealHand(String player) {
        String currentCard="";
        int tempIndex = -1;

       if(player.equals("p1")) {
           for(int i = 0; i < 10-playerOneWins; i++) {
               tempIndex =random.nextInt(deckCards.size());
               currentCard=deckCards.get(tempIndex);
               deckCards.remove(tempIndex);
               playerOneHand.add(currentCard);
           }
       } else {
           for(int i = 0; i < 10-playerTwoWins; i++) {
               tempIndex =random.nextInt(deckCards.size());
               currentCard=deckCards.get(tempIndex);
               deckCards.remove(tempIndex);
               playerTwoHand.add(currentCard);
           }

       }

    }

}
