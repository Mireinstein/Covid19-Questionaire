import java.util.InputMismatchException;
import java.util.Scanner;

class Person {
    boolean haveSymptoms;
    boolean isolatingOrQuarantining;
    boolean vaccinated;
    boolean closeContact;
    boolean tested;
    boolean waitingForResults;
    boolean travelled;
    String name;
    String secretCode;
    String[] responses = new String[7];


    public Person() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please Enter your name: ");
        this.name = scanner.nextLine();
        System.out.println("Please enter your secret word,\n" +
                "You will use this code to access your data");
        this.secretCode = scanner.nextLine();
    }

    void getUserData() {
        Scanner scanner = new Scanner(System.in);
        try {
            //Question1
            System.out.println("""
                    Regardless of your vaccination status, have\s
                    you experienced any of the symptoms in\s
                    the list below in the past 48 hours?
                    IMPORTANT: ANSWER “YES” EVEN IF YOU\s
                    BELIEVE THE SYMPTOM(S) IS BECAUSE OF\s
                    SOME OTHER MEDICAL CONDITION (FOR\s
                    EXAMPLE, ANSWER “YES” IF YOU HAVE A\s
                    RUNNY NOSE BECAUSE OF ALLERGIES).""");
            System.out.println("""
                    • fever or chills
                    • cough
                    • shortness of breath or difficulty breathing
                    • fatigue
                    • muscle or body aches
                    • headache
                    • new loss of taste or smell
                    • sore throat
                    • congestion or runny nose
                    • nausea or vomiting
                    • diarrhea""");
            String answer1 = scanner.nextLine();
            if ((answer1.toLowerCase().trim()).equals("yes")) {
                this.haveSymptoms = true;
                responses[0] = "Do you have covid-19 Symptoms?: Yes";
                System.out.println("Hi " + this.name + "! For the safety of other students, you can not attend this event.");
                generateStatistics(this.responses, this);
            } else if (((answer1.toLowerCase().trim()).equals("no"))) {
                responses[0] = "Do you have covid-19 Symptoms?: No";
                //Question2
                System.out.println("""
                        Are you isolating or quarantining because you\s
                        tested positive for COVID-19 or are worried\s
                        that you may be sick with COVID-19?""");
                String answer2 = scanner.nextLine();
                if ((answer2.toLowerCase().trim()).equals("yes")) {
                    this.isolatingOrQuarantining = true;
                    responses[1] = "Isolating or Quarantining?: Yes";
                    System.out.println("Hi " + this.name + "! For the safety of other students, you can not attend this event.");
                    generateStatistics(this.responses, this);
                    System.exit(0);
                } else if ((answer2.toLowerCase().trim()).equals("no")) {
                    //Question3
                    responses[1] = "Isolating or Quarantining?: No";
                    System.out.println("""
                             Are you fully vaccinated?* AND/OR\s
                            Have you recovered from a documented\s
                            COVID-19 infection in the last 3 months?
                            You don't have to answer this part if you don't want""");
                    String answer3 = scanner.nextLine();
                    if ((answer3.toLowerCase().trim()).equals("yes")) {
                        this.vaccinated = true;
                        responses[2] = "Fully Vaccinated?: Yes";
                    } else if ((answer3.toLowerCase().trim()).equals("no")) {
                        responses[2] = "Fully Vaccinated?: No";
                        this.vaccinated = true;
                    }

                    //Question 4
                    System.out.println("""
                            Have you been in close physical contact in\s
                            the last 14 days with:
                            • Anyone who is known to have\s
                            laboratory-confirmed COVID-19?
                            OR
                            • Anyone who has any symptoms\s
                            consistent with COVID-19?""");
                    String answer4 = scanner.nextLine();
                    if ((answer4.toLowerCase().trim()).equals("yes")) {
                        this.closeContact = true;
                        responses[3] = "Are you a close contact?: Yes";
                    } else if ((answer4.toLowerCase().trim()).equals("no")) {
                        responses[3] = "Are you a close contact?: No";
                    }
                    if (!(this.vaccinated) && (this.closeContact)) {
                        System.out.println("Hi " + this.name + "! For the safety of other students, you can not attend this event.");
                        generateStatistics(this.responses, this);
                        System.exit(0);
                    } else if ((this.vaccinated) && ((this.closeContact))) {
                        //Question5
                        System.out.println("Were you tested 5-7 days after your \n" +
                                "exposure with the close contact?");
                        String answer5 = scanner.nextLine();
                        if ((answer5.toLowerCase().trim()).equals("yes")) {
                            this.tested = true;
                            responses[4] = "Were you tested after exposure with the close contact?: Yes";
                        }
                        if (this.tested) {
                            //Certification
                            System.out.println("I certify that my responses are true and correct(Yes/No:)");
                            String certification = scanner.nextLine();
                            if ((certification.toLowerCase().trim()).equals("yes")) {
                                System.out.println("Hi " + this.name + "! You are allowed to attend the event");
                                generateStatistics(this.responses, this);
                                System.exit(0);
                            } else {
                                System.exit(0);
                            }

                        } else if (!(this.tested)) {
                            responses[4] = "Were you tested after exposure with the close contact?: No";
                            System.out.println("Hi " + this.name + "! For the safety of other students, you can not attend this event.");
                            generateStatistics(this.responses, this);
                            System.exit(0);
                        }
                    } else if ((this.vaccinated) && (!(this.closeContact))) {
                        //Certification
                        System.out.println("I certify that my responses are true and correct(Yes/No:)");
                        String certification = scanner.nextLine();
                        if ((certification.toLowerCase().trim()).equals("yes")) {
                            System.out.println("Hi " + this.name + "! You are allowed to attend the event");
                            generateStatistics(this.responses, this);
                            System.exit(0);
                        }

                    } else if ((!(this.vaccinated) && (!(this.closeContact)))) {
                        /* Q6 */
                        System.out.println("Are you currently waiting on the results of \n" +
                                "a COVID-19 test?\n" +
                                "IMPORTANT: ANSWER “NO” IF YOU ARE \n" +
                                "WAITING ON THE RESULTS OF A PRE-TRAVEL \n" +
                                "OR POST-TRAVEL COVID-19 TEST");
                        String answer6 = scanner.nextLine();
                        if ((answer6.toLowerCase().trim()).equals("yes")) {
                            this.waitingForResults = true;
                            responses[5] = " Are you waiting for covid-19 results?: Yes";
                            System.out.println("Hi " + this.name + "! You are allowed to attend the event");
                            generateStatistics(this.responses, this);
                            System.exit(0);
                        } else if ((answer6.toLowerCase().trim()).equals("no")) {
                            //Question 7
                            responses[5] = " Are you waiting for covid-19 results: No";
                            System.out.println("Have you traveled in the past 10 days?\n" +
                                    "Travel is defined as any trip that is overnight AND on \n" +
                                    "public transportation (plane, train, bus, Uber, Lyft, cab, \n" +
                                    "etc.) OR any trip that is overnight AND with people who \n" +
                                    "are not in your household");
                            String answer7 = scanner.nextLine();
                            if ((answer7.toLowerCase().trim()).equals("yes")) {

                                this.travelled = true;
                                responses[6] = "Have you travelled in the past five days?: Yes";
                                System.out.println("Hi " + this.name + "! You are  not allowed to attend the event");
                                generateStatistics(this.responses, this);
                                System.exit(0);
                            } else if ((answer7.toLowerCase().trim()).equals("no")) {
                                //Certification
                                //Certification
                                responses[6] = "Have you travelled in the past five days?: No";
                                System.out.println("I certify that my responses are true and correct(Yes/No:)");
                                String certification = scanner.nextLine();
                                if ((certification.toLowerCase().trim()).equals("yes")) {
                                    System.out.println("Hi " + this.name + "! You are allowed to attend the event");
                                    generateStatistics(this.responses, this);
                                    System.exit(0);
                                } else {
                                    System.exit(0);
                                }

                            }
                        }
                    }


                }

            }
        } catch (InputMismatchException exception) {
            scanner.nextLine();
            getUserData();
        }
    }

    void generateStatistics(String[] responses, Person person) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("A copy of your responses have been kept. If you would like to generate a copy of your responses\n," +
                " enter your secret Word\n" +
                "To exit press any letter");
        String secretCode = scanner.nextLine();

        if (secretCode.equals(person.secretCode)) {
            for (String response : responses) {
                if (!(response == ((null)))
                ) {
                    System.out.println(response);
                }

            }
        } else {
            System.exit(0);
        }


    }

}

public class CovidScreener {
    public static void main(String[] args) {
        run();
    }

    static void run() {
        Person person = new Person();
        person.getUserData();
    }
}
