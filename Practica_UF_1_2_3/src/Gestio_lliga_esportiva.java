package practica_uf1_2_3;

import java.io.*;           // imports de arrays, scanner i de excepcions
import java.util.Arrays;
import java.util.Scanner;

public class Gestio_lliga_esportiva {
    
    static boolean exit = false;    // Varible per sortir del menu
    
    public static String path_file = "equips\\Valorant_champions_equips.txt";   // Ruta al document amb els equips
    
    public static String[] equips = new String[20];         // Array de String on es guardaran els noms dels equips
    public static int[][]puntuacions = new int[20][100];    // Array bidimensional d'enters on es guardaran les partits i puntuacions a pocisio de cada equip
    
    public static void main(String[] args) throws IOException{
        
        Scanner sc = new Scanner(System.in);
        inici();        // Cridem priner de tot per escanejar el document txt
        
        
        do{             // El menu de gestio d'aplicacio
            System.out.println("~~~~~~~~~ Menu d'aplicació de gestió lliga esportiva ~~~~~~~~~");
            System.out.println("1. Visualitzar equips i seva puntuació");
            System.out.println("2. Afegir un equip nou amb les seves puntuacions");
            System.out.println("3. Modificar les dades d'un equip");
            System.out.println("4. Visualitzar dades del líder i del cuer de la lliga");
            System.out.println("5. Sortir i gurdar en fitxer les dades");
            
            int answ= sc.nextInt();
            
            System.out.println("____Opció: " + answ +"____");
            
            switch(answ){
                case 1:
                    visualitzar_equips();
                    break;
                case 2:
                    afegir_equip();
                    break;
                case 3:
                    modificar_equip();
                    break;
                case 4:
                    visualitzar_lider_cuer();
                    break;
                case 5: 
                    guardar();  // Al executa funcio de guardar ens sortira d'aplicacio, sino no ocurreix cap error
                    exit=true;
                    break;
                default:
                    System.out.println("Opcio no valida");
            }
            
        }while(!exit);
    }
    
    static void inici() throws FileNotFoundException, IOException{
        Scanner sc = new Scanner(new File(path_file));      // En el Scanner indiquem la ruta del document
        
        for(int i=0; sc.hasNextLine();i++){     // Llegim cada linia del txt
            String nom_equip = sc.next();   // Agafo la primera paraula de cada linia, seria el nom equip
            String linia = sc.nextLine();   // La resta de linia guardo aqui, son els punts separats per comes
            equips[i] = nom_equip;      // Noms d'equips guardo a l'Array
            
            linia = linia.replace("-", ""); // Trec el simbol del - de la linia
            String punts = linia.strip();   // Trec els espais que te la linia
            
            String p1 = punts.replace(",", " ");    // Cambio els comes per un espai
            
            int space = p1.indexOf(" ");    // Aqui indico el primer espai que troba
            int partits_jugats = Integer.parseInt(p1.substring(0,space));   // Des del simbol 0 de la linia agafo el numero enter fins al primer espai
            String p2 = p1.substring(space);    // Ara mateix la linia començar per un espai, trec l'espai perque el seguent simbol es el numero
            int partits_guanyats = Integer.parseInt(p2.substring(1,space+1));   // Faig la mateixa procedura amb cada numero
            String p3 = p2.substring(space+1);
            int partits_empatats = Integer.parseInt(p3.substring(1,space+1));
            String p4 = p3.substring(space+1);
            int partits_perduts = Integer.parseInt(p4.substring(1,space+1));
            String p5 = p4.substring(space+1);
            int punts_finals = Integer.parseInt(p5.substring(1));
            
            int cont=0; // Creo el contador per ficar cada numero a la seva posicio
            
            puntuacions[i][cont++] = partits_jugats;        // En posicio del equip em posara cada numero en posicions 0,1,2,3,4 
            puntuacions[i][cont++] = partits_guanyats;
            puntuacions[i][cont++] = partits_empatats;
            puntuacions[i][cont++] = partits_perduts;
            puntuacions[i][cont++] = punts_finals;
        }
    }
    
    static void visualitzar_equips(){   // Funcio simple, m'ensenya el contingut d'array d'equips i els puntuacions de cada equip
        for(int i=0; equips[i]!=null; i++){
            int cont=0;
            System.out.println(equips[i] + " Partits jugats: " + puntuacions[i][cont++] + " Partits guanyats: " + puntuacions[i][cont++] + " Partits empatats: " + puntuacions[i][cont++] + " Partits perduts: " + puntuacions[i][cont++] + " Punts totals: " + puntuacions[i][cont++]);
        }
    }
    
    static void afegir_equip(){         // Funcio per afegir equips
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Posar el nom del nou Equip: ");
        String nom = sc.next();

        for(int i=0; equips[i].equals(nom); i++){   // Fem una comprovacio si el nom d'equip ja esta agragat a l'array
            System.out.println("l'Equip ja existeix!");
            System.out.println("Vols tornar al menu principal? (si/no)");
            if(sc.next().equals("si")){         // Al ficar si, ens tornara al menu principal, sino torna a executar la funcio perque l'usuari pugui ficar un altre nom
                return;
            }else{
                afegir_equip();
            }
        }
        
        System.out.println("Posar els partits jugats: ");
        int partits_jugats = sc.nextInt();
        
        System.out.println("Posar els partits guanyats: ");
        int partits_guanyats = sc.nextInt();
        
        System.out.println("Posar els partits empatats: ");
        int partits_empatats = sc.nextInt();
        
        System.out.println("Posar els partits perduts: ");
        int partits_perduts = sc.nextInt();
        
        int i;
        for(i=0; equips[i]!=null ;i++){
            // Contador dels equips ja creats, ho fem per utilitzar la i despres per la primera posicio null que trobem, per posar l'equip
        }
        
        int cont=0;
        if(equips[i]==null){
            equips[i]=nom;
            puntuacions[i][cont++] = partits_jugats;    // A la mateixa posicio que l'equip, se'ns posaran els numeros
            puntuacions[i][cont++] = partits_guanyats;  
            puntuacions[i][cont++] = partits_empatats;  
            puntuacions[i][cont++] = partits_perduts;
        }
        calcul(partits_guanyats, partits_empatats, i, cont);    // Cridem el calcul dels punts totals, li importem els partits per fer el calcul, la 'i' i 'cont' per indica la pocisio dels punts totals
    }
    
    static void modificar_equip(){      // Funcio per modificar equips
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Posar el nom del Equip que vols modificar: ");
        String nom_mod = sc.next();
        
        int cont=0;
        int i=0;
        
        try{
            for(i=0; !equips[i].equals(nom_mod); i++){      // Revisem si l'equip que volem modificar exsisteixi a l'array
            }
        }catch (NullPointerException e){        // Si no existeix ens tornara al menu principal
            System.out.println("l'Equip no existeix");
            return;
        }
               
        for(i=0; !equips[i].equals(nom_mod); i++){
            // Aqui busquem en que posicio esta el equip posat, per poder fer el canvi de manera semblant que a la funcio afegir
        }

        System.out.println("Posar el nou nom del equip: ");
        String nom = sc.next();

        System.out.println("Posar els partits jugats: ");
        int partits_jugats = sc.nextInt();

        System.out.println("Posar els partits guanyats: ");
        int partits_guanyats = sc.nextInt();

        System.out.println("Posar els partits empatats: ");
        int partits_empatats = sc.nextInt();

        System.out.println("Posar els partits perduts: ");
        int partits_perduts = sc.nextInt();

        equips[i] = nom;                            // Renovem els arrays en mateixes posicions perque tot estigui correcte
        puntuacions[i][cont++] = partits_jugats;
        puntuacions[i][cont++] = partits_guanyats;
        puntuacions[i][cont++] = partits_empatats;
        puntuacions[i][cont++] = partits_perduts;

        calcul(partits_guanyats, partits_empatats, i, cont);    // Tornem a fer el calcul dels punts totals
    }
    
    static void calcul(int partits_guanyats, int partits_empatats, int i, int cont){    // Funcio per fer el calcul de punts totals
        
        int punts_totals = partits_guanyats*3+partits_empatats*1;   // Fem la operacio per obtindre el total de punts
        puntuacions[i][cont++] = punts_totals;              // La posem a la posicio importada
    }
    
    static void visualitzar_lider_cuer(){   // Funcio per visualizar quin equip te mes i menys punts en la lliga
        int i;
        int x=0;    // x i y farem servir despres per posar posicions de i amb mes puntuacio(x), i amb menys puntuacio(y) 
        int y=0;
        int posicio_punts=4;    // Com que necessitem nomes els punts totals de l'array, fiquem la posicio del numero
        int lider=puntuacions[0][posicio_punts];    // Agafem els punts del primer equip per fer la comparacio
        int cuer=puntuacions[0][posicio_punts];
        for(i=0; equips[i]!=null; i++){
            if(puntuacions[i][posicio_punts]>=lider){ // Comprovem si hi ha algun equip que supera el maxim
                lider=puntuacions[i][posicio_punts];
                x=i;
            }
        }
        for(i=0; equips[i]!=null; i++){
            if(puntuacions[i][posicio_punts]<=cuer){ // Comprovem si hi ha algun equip que supera el minim
                cuer=puntuacions[i][posicio_punts];
                y=i;
            }
        }
        System.out.println("Lider de la lliga: " + equips[x] + " Partits Jugats: " + puntuacions[x][0] + " Partits Guanyats: " + puntuacions[x][1] + " Partits Empatats: " + puntuacions[x][2] + " Partits Perduts: " + puntuacions[x][3] + " Punts Totals: " + puntuacions[x][4]);
        System.out.println("Cuer de la lliga: " + equips[y] + " Partits Jugats: " + puntuacions[y][0] + " Partits Guanyats: " + puntuacions[y][1] + " Partits Empatats: " + puntuacions[y][2] + " Partits Perduts: " + puntuacions[y][3] + " Punts Totals: " + puntuacions[y][4]);
    }
    
    static void guardar() throws IOException{       // Funcio que guardar la informacio actual dels arrays en un txt
        try{
            FileWriter fw = new FileWriter("equips\\Valorant_champions_equips.txt");    // La ruta del txt on guardem les dades

            for(int i=0; equips[i]!=null; i++){     // Llistem tots els dades i ho grabem en format que ens vagi be
                int cont=0;
                fw.write(equips[i] + " - " + puntuacions[i][cont++] + "," + puntuacions[i][cont++] + "," + puntuacions[i][cont++] + "," + puntuacions[i][cont++] + "," + puntuacions[i][cont++] + "\n");
            }
            fw.close();     // Tanquem el filewriter
            System.out.println("S'ha guardat Correctament!");
        }catch(IOException e){  // Si ocurreix algun error ens avisara
            System.out.println("Ha ocurrit un error");
            e.printStackTrace();
        }
    }    
}