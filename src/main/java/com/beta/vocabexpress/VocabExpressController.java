package com.beta.vocabexpress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

@Controller
public class VocabExpressController {

    private static final int UNDEFINED = -1;
    private static final int ADD_ENTRY = 0;
    private static final int TEST = 1;
    private static final int CLOSE_APP = 2;

    private EntryRepository entryRepository = new EntryRepository();
    private FileService fileService;
    private Scanner scanner = new Scanner(System.in);
    private ConsoleOutputWriter consoleOutputWriter;

    @Autowired
    VocabExpressController(EntryRepository entryRepository, FileService fileService, Scanner scanner, ConsoleOutputWriter consoleOutputWriter){
        this.entryRepository = entryRepository;
        this.fileService = fileService;
        this.scanner = scanner;
        this.consoleOutputWriter = consoleOutputWriter;
    }

    void mainLoop() {
        System.out.println("Welcome to VocabExpress");
        int option = UNDEFINED;
        while (option != CLOSE_APP) {
            printMenu();
            option = chooseOption();
            ExecuteOption(option);
        }

    }

    private void ExecuteOption(int option) {
        switch(option) {

            case ADD_ENTRY -> addEntry();
            case TEST -> test();
            case CLOSE_APP -> close();
            default -> consoleOutputWriter.println("Undefined option");
        }

    }

    private void test() {
        if(entryRepository.isEmpty()){
          consoleOutputWriter.println("Please add at least one phrase to the database");
          return;
        }
        final int testSize = Math.min(entryRepository.size(), 10);
        Set<Entry> randomEntries = entryRepository.getRandomEntries(testSize);
        int score = 0;
        for(Entry entry :randomEntries) {

            consoleOutputWriter.println(String.format("Please provide translation for :\"%s\"", entry.getOriginal()));
            String translation = scanner.nextLine();
            if(entry.getTranslation().equalsIgnoreCase(translation)){
                consoleOutputWriter.println("Correct answer!");
                score++;
            }else {
               consoleOutputWriter.println("Incorrect answer - "+entry.getTranslation());
            }



        }
        consoleOutputWriter.println(String.format("Your score: %d/%s\n", score, testSize));




    }

    private void addEntry() {
        consoleOutputWriter.println("Podaj oryginalną frazę");
        String original = scanner.nextLine();
        consoleOutputWriter.println("Podaj tłumaczenie");
        String translation = scanner.nextLine();
        Entry entry = new Entry(original, translation);
        entryRepository.add(entry);
    }

    private void close() {
        try {
            fileService.saveEntries(entryRepository.getAll());
            consoleOutputWriter.println("App status saved");
        } catch (IOException e) {
            consoleOutputWriter.println("Couldn't save the changes");
        }
        consoleOutputWriter.println("Bye Bye!");
    }

    private void printMenu() {
        consoleOutputWriter.println("Choose an option:");
        consoleOutputWriter.println("0 - Add a phrase");
        consoleOutputWriter.println("1 - Test");
        consoleOutputWriter.println("2 - Quit");
    }

    private int chooseOption() {
        int option;
        try {
            option = scanner.nextInt();
        } catch(InputMismatchException e) {
            option = UNDEFINED;
        } finally {
            scanner.nextLine();
        }
        if(option > UNDEFINED && option <= CLOSE_APP)
            return option;
        else
            return UNDEFINED;
    }


}
