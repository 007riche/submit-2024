package web.service.booking.client.cli;


import web.service.booking.client.models.Agency;
import web.service.booking.client.models.Client;
import web.service.booking.client.processors.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CLI {
    BufferedReader inputReader;
    Integerprocessor intProcessor;
    DoubleProcessor doubleProcessor;
    StringOnlyProcessor stringOnlyProcessor;
    RangedIntegerProcessor choiceProcessor;
    CardNumberProcessor cardNumberProcessor;
    Scanner scanner = new Scanner(System.in);
    int choice;
    Client user;
//    private booking.BookingService bookingProxy;


    public CLI() {
        Agency running = Agency.getInstance();
//        BookingServiceImplementationService serviceImplementationService = new BookingServiceImplementationService(url);
//        booking.BookingServiceImplementationService bookingServiceImplementationService = new booking.BookingServiceImplementationService(url);
//        new booking.BookingServiceImplementationService();
//        this.bookingProxy = bookingServiceImplementationService.getBookingServiceImplementationPort();
        choice = 0;
        this.inputReader = new BufferedReader(new InputStreamReader(System.in));
        this.doubleProcessor = new DoubleProcessor(this.inputReader);
        this.choiceProcessor = new RangedIntegerProcessor(this.inputReader);
        this.stringOnlyProcessor = new StringOnlyProcessor(this.inputReader);
        this.intProcessor = new Integerprocessor(this.inputReader);
        this.cardNumberProcessor = new CardNumberProcessor(this.inputReader);
        this.user = new Client("", "", "");
    }


    public void launch() {
        while (true) {
            choiceProcessor.setValidityCriterion(0, 2);
            System.out.println("1. Parcourir les hotels suggeres");
            System.out.println("2. Rechercher des hotels");
            System.out.println("3. Historique de vos reservations");
            System.out.println("0. Quitter l'app\n");
            try {
                choice = choiceProcessor.process();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (choice) {
                case 0:
                    System.out.println("\n\nAu Revoir. Deconnexion...");
                    System.exit(0);
                    break;
                case 1:

                    System.out.println("\n\n");
                    break;
                case 2:
                    String city = "";
                    int standart = -1;
                    double minPrice = -1;
                    double maxPrice = -1;
                    String arrivalDate;
                    String departureDate;
                    int numberPeople = -1;
                    try {
                        stringOnlyProcessor.setMesseage("Ville: ");
                        stringOnlyProcessor.setExceptionMesseage("Veuillez saisir une ville valide");
                        city = stringOnlyProcessor.process();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    do {
                        try {
                            intProcessor.setMesseage("Standart (Nombre D'etoiles entre 1 et 5): ");
                            standart = intProcessor.process();
                        } catch (IOException stars) {
                            stars.printStackTrace();
                        } catch (NumberFormatException stars) {
                            intProcessor.setExceptionMesseage("\nCECI N'EST PAS UN ENTIER\n");
                        }
                    } while (standart < 1 || standart > 5);

                    do {
                        try {
                            doubleProcessor.setMesseage("Prix minmum (ex: 0.0): ");
                            minPrice = doubleProcessor.process();
                            doubleProcessor.setMesseage("Prix minimum (ex: 0.0): ");
                        } catch (IOException min) {
                            min.printStackTrace();
                        }
                    } while (minPrice < 0);

                    do {
                        try {
                            doubleProcessor.setMesseage("Prix maximum (ex: 0.0): ");
                            maxPrice = doubleProcessor.process();
                        } catch (IOException onMax) {
                            onMax.printStackTrace();
                        }
                    } while (maxPrice <= 0.0 || maxPrice < minPrice);

                    System.out.print("Date d'arrivee: ");
                    arrivalDate = scanner.next();
                    System.out.print("Date de depart: ");
                    departureDate = scanner.next();
                    do {
                        try {
                            intProcessor.setMesseage("Nombre de personne a heberger (ne pas exceder 50 personnes): ");
                            intProcessor.setExceptionMesseage("Veuillez entrer un numobre valide de personne(entier)");
                            numberPeople = intProcessor.process();
                        } catch (IOException onPers) {
                            onPers.printStackTrace();
                        }
                    } while (numberPeople < 1 && numberPeople > 50);

                        System.out.println("Voulez vous reserver?");
                        String firstName="";
                        String lastName="";
                        String paymentCardNumber = "";
                        String bookingStatus = "";

                        if (user.getFirstName().isEmpty()) {
                            stringOnlyProcessor.setMesseage("Veuillez entrer le nom du reservataire: ");
                            try {
                                firstName = stringOnlyProcessor.process();
                            } catch (IOException e) {
                            }
                            user.setFirstName(firstName);
                        }

                        if (user.getLastName().isEmpty()) {
                            stringOnlyProcessor.setMesseage("Veuillez entrer les prenoms du reservataire:: ");
                            try {
                                lastName = stringOnlyProcessor.process();
                            } catch (IOException e) {
//                            throw new RuntimeException(e);
                            }

                            user.setLastName(lastName);
                        }

                        try {
                            paymentCardNumber = cardNumberProcessor.process();
                        } catch (IOException e) {
//                            throw new RuntimeException(e);
                        }  }
                    break;
            }

        }

    }
//}





//        if (foundHotel.size() == 0) {
//        System.out.println("AUCUN RESULTAT POUR CETTE RECHERCHE");
//        } else {
//        for (int i = 0; i < foundHotel.size(); i++) {
//        System.out.println("\t" + (i + 1) + ": Hotel: " + foundHotel.get(i).getName() + " Ville: " + foundHotel.get(i).getCity() + " Etoiles: " + foundHotel.get(i).getStars() + "\u272D ");
//        }
//        System.out.println("\n\n");
//        choiceProcessor.setValidityCriterion(0, foundHotel.size());
//        try {
//        choice = choiceProcessor.process();
//        } catch (IOException e) {
//        e.printStackTrace();
//        }
//        int allrooms = foundHotel.get(choice - 1).getAvailalbleRooms().size();


//                        bookingStatus = proxy.makeBooking(user.getFirstName(), user.getLastName(), paymentCardNumber, (List<Room>) foundHotel.get(choice-1));
//        bookingStatus = proxy.makeBooking(user.getFirstName(),user.getLastName(),paymentCardNumber, (List<Room>) foundHotel.get(choice-1));

