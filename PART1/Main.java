import java.util.Scanner;



public class Main {
        private static Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
            int distance = enterDistance();
            final int numHorses = 3;
            Race race = new Race(distance); 
            Horse[] horses = declareHorses(numHorses);
            race = addAllHorses(horses, race);
            race.startRace(); // Start the race
            scanner.close(); // Close the scanner when done with input
        }


            public static Race addAllHorses(Horse[] horses, Race race) {
                for (int i=0; i<horses.length; i++) {
                    race.addHorse(horses[i], i+1);
                }
                return race;
            }




            public static Horse[] declareHorses(int numHorses) {
                Horse[] horses = new Horse[numHorses];
                for (int i = 0; i < numHorses; i++) {
                    boolean valid = false;
                    String name = input("Enter the name of horse " + (i + 1) + ": ");
                    double confidence = 0;
                    while (!valid) {
                        try {
                            System.out.print("Enter the confidence of horse " + (i + 1) + ": ");
                            confidence = Double.parseDouble(scanner.nextLine());
                            if (confidence >= 0 && confidence <= 1) {
                                valid = true;
                            } else {
                                System.out.println("Confidence must be between 0 and 1.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a decimal number.");
                        }
                    }
                    horses[i] = new Horse('\u2658', name, confidence);
                }
                return horses;
            }

            public static int enterDistance() {
                int distance = 0;
                boolean valid = false;
                while (!valid) {
                    try {
                        System.out.print("Enter the distance of the race: ");
                        distance = Integer.parseInt(scanner.nextLine());
                        if (distance > 0) {
                            valid = true;
                        } else {
                            System.out.println("Distance must be greater than 0.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                    }
                }
                return distance;
            }

            public static String input(String message){
                System.out.print(message);
                return scanner.nextLine();
            }    
        }
    


//private static Scanner scanner = new Scanner(System.in);
// public static void main(String[] args) {
//     int distance = enterDistance();
//     final int numHorses = 3;
//     Race race = new Race(distance); 
//     Horse[] horses = declareHorses(numHorses);
//     race = addAllHorses(horses, race);
//     race.startRace(); // Start the race
//     scanner.close(); // Close the scanner when done with input
// }


//     public static Race addAllHorses(Horse[] horses, Race race) {
//         for (int i=0; i<horses.length; i++) {
//             race.addHorse(horses[i], i+1);
//         }
//         return race;
//     }




//     public static Horse[] declareHorses(int numHorses) {
//         Horse[] horses = new Horse[numHorses];
//         for (int i = 0; i < numHorses; i++) {
//             boolean valid = false;
//             String name = input("Enter the name of horse " + (i + 1) + ": ");
//             double confidence = 0;
//             while (!valid) {
//                 try {
//                     System.out.print("Enter the confidence of horse " + (i + 1) + ": ");
//                     confidence = Double.parseDouble(scanner.nextLine());
//                     if (confidence >= 0 && confidence <= 1) {
//                         valid = true;
//                     } else {
//                         System.out.println("Confidence must be between 0 and 1.");
//                     }
//                 } catch (NumberFormatException e) {
//                     System.out.println("Invalid input. Please enter a decimal number.");
//                 }
//             }
//             horses[i] = new Horse('\u2658', name, confidence);
//         }
//         return horses;
//     }

//     public static int enterDistance() {
//         int distance = 0;
//         boolean valid = false;
//         while (!valid) {
//             try {
//                 System.out.print("Enter the distance of the race: ");
//                 distance = Integer.parseInt(scanner.nextLine());
//                 if (distance > 0) {
//                     valid = true;
//                 } else {
//                     System.out.println("Distance must be greater than 0.");
//                 }
//             } catch (NumberFormatException e) {
//                 System.out.println("Invalid input. Please enter an integer.");
//             }
//         }
//         return distance;
//     }

//     public static String input(String message){
//         System.out.print(message);
//         return scanner.nextLine();
//     }    
// }