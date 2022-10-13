import javax.sound.sampled.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

import static java.lang.System.exit;

public class Intro {

    public static final String PLAIN = "\033[0;0m";
    public static final String BOLD = "\033[0;1m";
    public static final String RESET = "\u001B[0m";
    public static final String YELLOW_FOREGROUND = "\u001B[33m";
    public static final String CYAN_FOREGROUND = "\u001B[36m";
    public static final String RED_FOREGROUND = "\u001B[31m";
    public static final String BLUER_FOREGROUND = "\033[1;34m";
    public static final String BLUE_BOLD_BRIGHT_FOREGROUND = "\033[1;94m";
    public static final String YELLOWER_FOREGROUND = "\033[1;33m";
    public static final String PURPLE_BRIGHT_FOREGROUND = "\033[0;95m";
    public static final String GREEN_BOLD_BRIGHT_FOREGROUND = "\033[1;92m";
    public static final String CYAN_BOLD_BRIGHT_FOREGROUND = "\033[1;96m";
    public static final String WHITE_BOLD_BRIGHT_FOREGROUND = "\033[1;97m";
    public static final String PURPLE_BOLD_BRIGHT_FOREGROUND = "\033[1;95m";


    private static final Random DRAWN = new Random();

    public void Intro1(String[] options) {

        printMenuHeader();
        for (String option : options) {
            System.out.println(option);
            waitFor(100);
        }

    }

    private void printMenuHeader() {
        System.out.print(BOLD + YELLOW_FOREGROUND +
                " ╔╦╗  ╔═╗  ╦  ╔╗╔    ╔╦╗  ╔═╗  ╔╗╔  ╦ ╦\n" +
                " ║║║  ╠═╣  ║  ║║║    ║║║  ║╣   ║║║  ║ ║\n" +
                " ╩ ╩  ╩ ╩  ╩  ╝╚╝    ╩ ╩  ╚═╝  ╝╚╝  ╚═╝\n" + PLAIN + RED_FOREGROUND +
                "▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞\n" + RESET);

    }

    public void goIntro(String[] args) {
        playSound("sounds/HAL_9000.wav");

        try {
            simulateLoading();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }


    }

    public void quoteOfTheDay() {
        final String[] QUOTES = {"Kill them Johny. Kill them all.“ (Unknown)",
                " “Evil’s just destructive? Then storms are evil if it’s that simple." +
                        " And we have a fire, and there’s hail. Underwriters lump it all under ‘Acts of God.’ (Hannibal Lecter)”",
                " “Being smart spoils a lot of things, doesn’t it?“ (Hannibal Lecter)",
                " “Knwxn cwcs spcow!“ (Guaycjjtr)", " “o0o0oooOooo)OO)oo0ooO)(oo...“  {Oo0oo)",
                " “I do wish we could chat longer, but I’m having an old friend for dinner.“ (Hannibal Lecter)",
                " “Remarkable boy. I do admire your courage. I think I’ll eat your heart!” (Hannibal Lecter)",
                " “You fly back to school now, little Starling. Fly, fly, fly. Fly, fly, fly.” (Hannibal Lecter)"};

        playSound("sounds/well.wav");
        String quote = drawFromArray(QUOTES);
        fakeCLS();
        System.out.println("\n");
        System.out.println(quote);
    }

    public String drawFromArray(String[] input) {
        if (input != null || input.length > 0) {

        }

        return input[DRAWN.nextInt(input.length)];
    }


    public void showHistoryOfDraughts() {
        fakeCLS();
        playSound("sounds/puzzling.wav" +
                "");
        System.out.println(PURPLE_BRIGHT_FOREGROUND +
                "   _    ,                                                               \n" +
                "  ' )  /      _/_                   /)      /                  /  _/_   \n" +
                "   /--/ o _   /  __ __  __  ,   __ //    __/ __  __.  . . _,  /_  /  _  \n" +
                "  /  (_<_/_)_<__(_)/ (_/ (_/_  (_)//_   (_/_/ (_(_/|_(_/_(_)_/ /_<__/_)_\n" +
                "                          /      />                       /|            \n" +
                "                         '      </                       |/     \n\n" + BLUE_BOLD_BRIGHT_FOREGROUND);


        System.out.println(
                "    A board resembling a draughts board was found  in Ur, an important\n" +
                        "      Sumerain city-state in ancient Mesopotamia dating from 3000 BC.\n\n" +
                        "    Polish Draughts are larger than standard draughts (100 instead of 64 fields)\n" +
                        "      and have 4 rows of pawns instead of 3 rows of pawns (20 pawns vs. 12 pawns in standard draughts).\n\n" +
                        "    Polish Draughts were invented by Polish officer Franciszek Żubr,\n" +
                        "      a member of the Maria Leszczyńska and Louis XV royal court .\n" +
                        "      ( Maria Leszczyńska was Stanisław Leszczyński's - Polish king - daughter. )\n\n" +
                        "    In Poland this draughts type until XIX century was called the French Draughts.\n\n" +
                        "    The game was very popular among the nobility and French nobel ladies usually played with black pawns\n" +
                        "      to contrast it with the whiteness of their skin (since tanning was not fashionable at that time in Europe).\n\n" + RESET);

    }

    public void showInstructions() {
        playSound("sounds/mind_is_going.wav");
        System.out.println(BLUER_FOREGROUND + "\n\n\n\n\n\n\n" + YELLOWER_FOREGROUND);
        System.out.println("       __ __  __  __  ______ ____  __ __   ___ ______ __   ___   __  __  __ \n" +
                "       || ||\\ || (( \\ | || | || \\\\ || ||  //   | || | ||  // \\\\  ||\\ || (( \\\n" +
                "       || ||\\\\||  \\\\    ||   ||_// || || ((      ||   || ((   )) ||\\\\||  \\\\ \n" +
                "       || || \\|| \\_))   ||   || \\\\ \\\\_//  \\\\__   ||   ||  \\\\_//  || \\|| \\_))\n\n" +
                RESET + "       ===========================================================================\n");
        System.out.println(WHITE_BOLD_BRIGHT_FOREGROUND +
                "       The goal of Polish Draughts Game is to remove all your opponent's pieces\n" +
                "        from the board or block its ability to move with the remaining pawns.\n\n" +
                "       Your pieces can only move forward one tile diagonally.\n\n" +
                "       To capture an opponent's piece and remove it from the board,\n" +
                "        you need to jump over their piece with one of yours.\n\n" +
                "       If one of your pieces gets to the opposite side of the board (the last row),\n" +
                "        it will turn into a Queen.\n" +
                "       Queens can move and jump diagonally in any direction at any distance.\n\n" + RED_FOREGROUND +
                "                               The marvellous Team ONE wish you good luck!\n" + RESET);

    }

    public void startThatBrutalGame() {
        fakeCLS();
        playSound("sounds/quite_sure.wav");
        printItSlow("  Tu coś na sekundę max można mignąć");
        fakeCLS();
    }

    public static void teamInfo() {
        fakeCLS();
        playSound("sounds/l_ysium.wav");
        printTeamONE();
        waitFor(1000);
    }


    public static void fakeCLS() {
        for (int i = 0; i < 10; i++)
            System.out.println("\n\n\n\n");
    }

    public static void waitFor(int delay) throws RuntimeException {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void playSound(String soundFile) {
        File f = new File("./" + soundFile);
        AudioInputStream audioIn = null;
        try {
            audioIn = AudioSystem.getAudioInputStream(f.toURI().toURL());
        } catch (UnsupportedAudioFileException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        }
        try {
            clip.open(audioIn);
        } catch (LineUnavailableException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        clip.start();
    }

    private static String lastLine = "";

    public static void print(String line) {
        //clear the last line if longer
        if (lastLine.length() > line.length()) {
            String temp = "";
            for (int i = 0; i < lastLine.length(); i++) {
                temp += " ";
            }
            if (temp.length() > 1)
                System.out.print("\r" + temp);
        }
        System.out.print("\r" + line);
        lastLine = line;
    }

    private static byte anim;

    public static void animate(String line) {
        switch (anim) {
            case 1:
                print("[ \\ ] " + line);
                break;
            case 2:
                print("[ | ] " + line);
                break;
            case 3:
                print("[ / ] " + line);
                break;
            default:
                anim = 0;
                print("[ - ] " + line);
        }
        anim++;
    }

    public static void simulateLoading() throws InterruptedException {
        Intro consoleHelper = new Intro();
        for (int i = 0; i < 102; i += 2) {
            animate(i + "");
            Thread.sleep(80);
        }
    }

    public void printTitle1() {
        fakeCLS();
        System.out.println(GREEN_BOLD_BRIGHT_FOREGROUND +
                "8888888b.          888 d8b          888           8888888b.                                    888      888             \n" +
                "888   Y88b         888 Y8P          888           888  \"Y88b                                   888      888             \n" +
                "888    888         888              888           888    888                                   888      888             \n" +
                "888   d88P .d88b.  888 888 .d8888b  88888b.       888    888 888d888 8888b.  888  888  .d88b.  88888b.  888888 .d8888b  \n" +
                "8888888P\" d88\"\"88b 888 888 88K      888 \"88b      888    888 888P\"      \"88b 888  888 d88P\"88b 888 \"88b 888    88K      \n" +
                "888       888  888 888 888 \"Y8888b. 888  888      888    888 888    .d888888 888  888 888  888 888  888 888    \"Y8888b. \n" +
                "888       Y88..88P 888 888      X88 888  888      888  .d88P 888    888  888 Y88b 888 Y88b 888 888  888 Y88b.       X88 \n" +
                "888        \"Y88P\"  888 888  88888P' 888  888      8888888P\"  888    \"Y888888  \"Y88888  \"Y88888 888  888  \"Y888  88888P' \n" +
                "                              _                      ___             _     __              888                          \n" +
                "   _    |_  __   _|_ _  |    (_| _ __  _    |_  \\/    |  _  _ __    / \\|\\||_          Y8b d88P                          \n" +
                "  (_|   |_) | |_| |_(_| |    __|(_||||(/_   |_) /     | (/_(_||||   \\_/| ||__          \"Y88P\"\n" + RESET);
    }

    public void printTitle2() {
        System.out.println(CYAN_FOREGROUND +
                "█ ▄▄  ████▄ █    ▄█    ▄▄▄▄▄    ▄  █     ██▄   █▄▄▄▄ ██     ▄     ▄▀   ▄  █    ▄▄▄▄▀ ▄▄▄▄▄ \n" +
                "█   █ █   █ █    ██   █     ▀▄ █   █     █  █  █  ▄▀ █ █     █  ▄▀    █   █ ▀▀▀ █   █     ▀▄ \n" +
                "█▀▀▀  █   █ █    ██ ▄  ▀▀▀▀▄   ██▀▀█     █   █ █▀▀▌  █▄▄█ █   █ █ ▀▄  ██▀▀█     █ ▄  ▀▀▀▀▄ \n" +
                "█     ▀████ ███▄ ▐█  ▀▄▄▄▄▀    █   █     █  █  █  █  █  █ █   █ █   █ █   █    █   ▀▄▄▄▄▀ \n" +
                " █              ▀ ▐               █      ███▀    █      █ █▄ ▄█  ███     █    ▀ \n" +
                "  ▀                              ▀              ▀      █   ▀▀▀          ▀ \n" +
                "                                                      ▀ " + RESET + "\n");
    }

    public static void printTeamONE() {
        System.out.println(YELLOWER_FOREGROUND);
        printItSlow("   ████████╗███████╗ █████╗ ███╗   ███╗     ██████╗ ███╗   ██╗███████╗");
        System.out.println(BLUER_FOREGROUND);
        printItSlow("   ╚══██╔══╝██╔════╝██╔══██╗████╗ ████║    ██╔═══██╗████╗  ██║██╔════╝");
        System.out.println(YELLOWER_FOREGROUND);
        printItSlow("      ██║   █████╗  ███████║██╔████╔██║    ██║   ██║██╔██╗ ██║█████╗  ");
        System.out.println(BLUER_FOREGROUND);
        printItSlow("      ██║   ██╔══╝  ██╔══██║██║╚██╔╝██║    ██║   ██║██║╚██╗██║██╔══╝  ");
        System.out.println(YELLOWER_FOREGROUND);
        printItSlow("      ██║   ███████╗██║  ██║██║ ╚═╝ ██║    ╚██████╔╝██║ ╚████║███████╗");
        System.out.println(BLUER_FOREGROUND);
        printItSlow("      ╚═╝   ╚══════╝╚═╝  ╚═╝╚═╝     ╚═╝     ╚═════╝ ╚═╝  ╚═══╝╚══════╝");
        System.out.println("");
        System.out.println(PURPLE_BOLD_BRIGHT_FOREGROUND + "\n");
        printItSlow("             ..... M A G D A L E N A .. O C H ...............");
        System.out.println(GREEN_BOLD_BRIGHT_FOREGROUND + "\n");
        printItSlow("             ...... D A W I D .. D E S Z C Z ................");
        System.out.println(CYAN_BOLD_BRIGHT_FOREGROUND + "\n");
        printItSlow("             ....... B A R T O S Z .. K A W A L E R .........");
        System.out.println(GREEN_BOLD_BRIGHT_FOREGROUND + "\n");
        printItSlow("             ........ S E B A S T I A N .. S O S I N ........");
        System.out.println(PURPLE_BOLD_BRIGHT_FOREGROUND + "\n");
        printItSlow("             ......... M A R C I N .. S Z U W A L S K I .....");
        System.out.println(RESET + '\n');


        for (int i = 0; i < 5; i++) {
            System.out.println("");
            waitFor(220 * i);
        }
    }


    public static void printItSlow(String sayWhat) {
        for (int i = 0; i < sayWhat.length(); i++) {
            System.out.print(sayWhat.charAt(i));
            if (sayWhat.charAt(i) == ' ') continue;
            waitFor(15);
        }

    }

    public static void printItFast(String sayWhat) {
        for (int i = 0; i < sayWhat.length(); i++) {
            System.out.print(sayWhat.charAt(i));
            if (sayWhat.charAt(i) == ' ') continue;
            waitFor(5);
        }

    }

    public void printDocumentation() {
        playSound(("sounds/chimera.wav"));
        fakeCLS();
        System.out.println("\n\n" + RED_FOREGROUND);
        System.out.println("                              ...\n" +
                "           s,                .                    .s\n" +
                "            ss,              . ..               .ss\n" +
                "            'SsSs,           ..  .           .sSsS'\n" +
                "             sSs'sSs,        .   .        .sSs'sSs\n" +
                "              sSs  'sSs,      ...      .sSs'  sSs\n" +
                "               sS,    'sSs,         .sSs'    .Ss\n" +
                "               'Ss       'sSs,   .sSs'       sS'\n" +
                "      ...       sSs         ' .sSs'         sSs       ...\n" +
                "     .           sSs       .sSs' ..,       sSs       .\n" +
                "     . ..         sS,   .sSs'  .  'sSs,   .Ss        . ..\n" +
                "     ..  .        'Ss .Ss'     .     'sSs. ''        ..  .\n" +
                "     .   .         sSs '       .        'sSs,        .   .\n" +
                "      ...      .sS.'sSs        .        .. 'sSs,      ...\n" +
                "            .sSs'    sS,     .....     .Ss    'sSs,\n" +
                "         .sSs'       'Ss       .       sS'       'sSs,\n" +
                "      .sSs'           sSs      .      sSs           'sSs,\n" +
                "   .sSs'____________________________ sSs ______________'sSs,\n" +
                ".sSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSSS'.Ss SSSSSSSSSSSSSSSSSSSSSs,\n" +
                "                        ...         sS'\n" +
                "                         sSs       sSs\n" +
                "                          sSs     sSs       - LCF\n" +
                "                           sS,   .Ss\n" +
                "                           'Ss   sS'\n" +
                "                            sSs sSs\n" +
                "                             sSsSs\n" +
                "                              sSs\n" +
                "                               s");
        waitFor(5000);
        System.out.println("\n  Press ENTER to hail Satan!!!\n" + RESET);
    }

    public static void playIntro() {
        Intro consoleHelper = new Intro();
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);
        consoleHelper.printTitle1();
        playSound("sounds/HAL_9000.wav");
        System.out.println(" Loading...");

        try {
            simulateLoading();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("\n Press ENTER to coninue");
        waitForEnter();

        System.out.println("\n");
        consoleHelper.waitFor(100);
        consoleHelper.fakeCLS();


        String[] options = {" [1] - Start the game",
                " [2] - Game instructions",
                " [3] - History of polish draughts",
                " [4] - The great team behind",
                " [5] - Random quote of the day",
                " [6] - Board theme selection",
                " [7] - Exit"
        };

        playSound("sounds/just_a_moment.wav");
        consoleHelper.printTitle2();
        int option = 1;
        while (option != 7) {
            consoleHelper.Intro1(options);
            try {
                option = scanner.nextInt();
                switch (option) {
                    case 1:
                        consoleHelper.startThatBrutalGame();
                        game.start();
                        break;
                    case 2:
                        consoleHelper.showInstructions();
                        System.out.println("           Press ENTER to go back");
                        waitForEnter();
                        break;
                    case 3:
                        consoleHelper.showHistoryOfDraughts();
                        System.out.println("           Press ENTER to continue");
                        waitForEnter();
                        break;
                    case 4:
                        teamInfo();
                        System.out.println(BLUER_FOREGROUND + "              " +
                                "     Press [ENTER] if you love Team ONE..." + RESET);
                        waitForEnter();
                        break;
                    case 5:
                        consoleHelper.quoteOfTheDay();
                        break;
                    case 6:
                        selectBoardTheme();
                        break;
                    case 7:
                        exit(0);
                    case 666:
                        consoleHelper.printDocumentation();
                        waitForEnter();
                        break;
                    default: {
                        System.out.println(RED_FOREGROUND + "   Hey, that's not how we get along. Follow the instructions below." + RESET);
                        System.out.println("   Please choose an option (number between 1 and " + options.length + ")");
                        Toolkit.getDefaultToolkit().beep();
                    }
                }
            } catch (Exception ex) {
                System.out.println(RED_FOREGROUND + "   Well, that's not how we get along. Follow the instructions below." + RESET);
                System.out.println("   Please choose an option (number between 1 and " + options.length + ")...\n");
                playSound("sounds/enough_info.wav");
                scanner.next();
            }
        }
    }

    private static void selectBoardTheme() {
        fakeCLS();
        System.out.println("\n\n " +
                GREEN_BOLD_BRIGHT_FOREGROUND + "       ███        ▄█    █▄       ▄████████ " + CYAN_BOLD_BRIGHT_FOREGROUND + "  ▄▄▄▄███▄▄▄▄      ▄████████    ▄████████ \n" +
                GREEN_BOLD_BRIGHT_FOREGROUND + "   ▀█████████▄   ███    ███     ███    ███ " + CYAN_BOLD_BRIGHT_FOREGROUND + "▄██▀▀▀███▀▀▀██▄   ███    ███   ███    ███ \n" +
                GREEN_BOLD_BRIGHT_FOREGROUND + "      ▀███▀▀██   ███    ███     ███    █▀  " + CYAN_BOLD_BRIGHT_FOREGROUND + "███   ███   ███   ███    █▀    ███    █▀  \n" +
                GREEN_BOLD_BRIGHT_FOREGROUND + "       ███   ▀  ▄███▄▄▄▄███▄▄  ▄███▄▄▄     " + CYAN_BOLD_BRIGHT_FOREGROUND + "███   ███   ███  ▄███▄▄▄       ███        \n" +
                WHITE_BOLD_BRIGHT_FOREGROUND + "       ███     ▀▀███▀▀▀▀███▀  ▀▀███▀▀▀     " + PURPLE_BOLD_BRIGHT_FOREGROUND + "███   ███   ███ ▀▀███▀▀▀     ▀███████████ \n" +
                WHITE_BOLD_BRIGHT_FOREGROUND + "       ███       ███    ███     ███    █▄  " + PURPLE_BOLD_BRIGHT_FOREGROUND + "███   ███   ███   ███    █▄           ███ \n" +
                WHITE_BOLD_BRIGHT_FOREGROUND + "       ███       ███    ███     ███    ███ " + PURPLE_BOLD_BRIGHT_FOREGROUND + "███   ███   ███   ███    ███    ▄█    ███ \n" +
                WHITE_BOLD_BRIGHT_FOREGROUND + "      ▄████▀     ███    █▀      ██████████ " + PURPLE_BOLD_BRIGHT_FOREGROUND + " ▀█   ███   █▀    ██████████  ▄████████▀  \n" +
                BLUE_BOLD_BRIGHT_FOREGROUND + "\n\n");
        System.out.println("          Please choose your board template:\n" + RESET);
        System.out.println("         ▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞▞\n" + RESET);

        int option = 1;
        String[] subOptions = {
                "    (1) - Leather style",
                "    (2) - Sea",
                "    (3) - nazwa",
                "    (4) - The great team behind",
                "    (5) - Random quote of the day",
                "    (6) - Board theme selection",
                "    (7) - Board theme selection",
                "    (8) - Exit"
        };
        Scanner subScanner = new Scanner(System.in);
        int subMenu = 1;
        while (subMenu != 8) {
            for (String subOption : subOptions) {
                System.out.println(subOption);
                waitFor(100);
            }
            try {
                subMenu = subScanner.nextInt();
                switch (subMenu) {
                    case 1: {
                        // odpal Leather
                        break;
                    }
                    case 2: {
                        System.exit(1);
                        break;
                    }
                    case 3: {
                        System.exit(1);
                        break;
                    }
                    case 4: {
                        System.exit(0);
                        break;
                    }
                    case 5: {
                        System.exit(0);
                        break;
                    }
                    case 6: {
                        System.exit(0);
                        break;
                    }
                    case 7: {
                        System.exit(0);
                        break;
                    }
                    case 8: {
                        break;
                    }
                    default: {
                        System.out.println("   Please choose an option (number between 1 and 8");
                    }

                }

            } catch (Exception ex) {
                System.out.println(RED_FOREGROUND + "   Digits 1 to 8 are allowed only\n" + RESET);
                playSound("sounds/enough_info.wav");
                subScanner.next();
            }
        }
    }


    public static void waitForEnter() {
        Scanner s666 = new Scanner(System.in);
        String ave = " ";
        ave = s666.nextLine();
    }


}