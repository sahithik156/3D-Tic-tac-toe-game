package tictactoe;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    static boolean isFull(char[][][] game){// to check if the board is full
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(game[i][j][k]=='E'){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    static int winChance(char[][][] game,ArrayList arr, int n){// returns a place where best possible collinear line can be made
       int place=combination(game,arr, n);
       return place;
    }
    static void printCount(char[][][] game,ArrayList arr, int n, char c){
        int count=countLines(game,arr,n);
        if(c=='O'){
            System.out.println("collinear lines of computer = "+count);
        }
        else{
            System.out.println("collinear lines of Human = "+count);
        }
    }
    static int[] insertBest(char game[][][],ArrayList CList){//returns an array with best position and frequency(number of colliner lines that can be made after that move) when computer has nothing to win or block 
        int[][][] mgc=magicCube.MagicCube();
        int[] best=new int[2];
        ArrayList<Integer> Empty = new ArrayList<Integer>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(game[i][j][k]=='E'){
                        Empty.add(mgc[i][j][k]);
                    }
                }
            }
        }
       
        for(int i=0;i<Empty.size();i++){
            int count=0;
            for(int j=0;j<CList.size();j++){
                int a=(int) Empty.get(i);
                int b=(int) CList.get(j);          
                int t=42-a-b;
                if(0<t&&t<=27&&Empty.contains(t)){
                    if(collinear(a,b)){                       
                        count=count+1;
                    }
                }
            }
            if(best[0]==0&&best[1]==0){
                best[0]=Empty.get(i);
                best[1]=count;
            }
            else{
                if(best[1]<count){
                    best[0]=Empty.get(i);
                    best[1]=count;
                }
            }
            count=0;
           
        }
        return best;
    }
    static int[] bestInsertCondition(char game[][][],ArrayList CList,ArrayList arr){//returns best position for computer to win or best position for the computer to block 
        int[][][] mgc=magicCube.MagicCube();
        int[] best=new int[2];
        best[0]=0;
        best[1]=0;
       
        for(int i=0;i<arr.size();i++){
            int count=0;
            for(int j=0;j<CList.size();j++){
                int a=(int) arr.get(i);
                int b=(int) CList.get(j);
                int t=42-a-b;
                if(0<t&&t<=27&&arr.contains(t)){
                    if(collinear(a,b)){
                        count=count+1;
                    }
                }
            }
            if(best[0]==0&&best[1]==0){
                best[0]=(int) arr.get(i);
                best[1]=count;
            }
            else{
                if(best[1]<count){
                    best[0]=(int) arr.get(i);
                    best[1]=count;
                }
            }
            count=0;
           
        }
        return best;
    }
   
    static int insertCorner(char game[][][]){// to insert at a corner position
        if(isEmpty(16,game)){
            return 16;
        }
        if(isEmpty(21,game)){
            return 21;
        }
        if(isEmpty(20,game)){
            return 20;
        }
        if(isEmpty(4,game)){
            return 4;
        }
        if(isEmpty(24,game)){
            return 24;
        }
        if(isEmpty(8,game)){
            return 8;
        }
        if(isEmpty(7,game)){
            return 7;
        }
        if(isEmpty(12,game)){
            return 12;
        }
        return -1;
       
    }
    
    static boolean isEmpty(int x,char game[][][]){//to check if a position is empty
        int p[]=getIndex(x, magicCube.MagicCube());
        return (game[p[0]][p[1]][p[2]]=='E');
       
    }
    static boolean collinear(int a, int b){// to check if three positions are collinear

        int p[]=getIndex(a,magicCube.MagicCube());
        int q[]=getIndex(b,magicCube.MagicCube());
        int r[]=getIndex((42-a-b),magicCube.MagicCube());
        double s[]=new double[3];
        double t[]=new double[3];
        s[0]=((p[0]-q[0])*(1.0));
        s[1]=((p[1]-q[1])*(1.0));
        s[2]=((p[2]-q[2])*(1.0));
        t[0]=((r[0]-q[0])*(1.0));
        t[1]=((r[1]-q[1])*(1.0));
        t[2]=((r[2]-q[2])*(1.0));
        double d=Math.sqrt((Math.pow(s[0], 2)+Math.pow(s[1], 2)+Math.pow(s[2], 2))*(Math.pow(t[0], 2)+Math.pow(t[1], 2)+Math.pow(t[2], 2)));
        if(d==0){
            return false;
        }
        else{
        double cosine=(s[0]*t[0]+s[1]*t[1]+s[2]*t[2])/d;
            return cosine==-1||cosine==1;
        }
    }
    static int countLines(char[][][] game,ArrayList arr, int n){// to count the number of collinear lines
        int count=0;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int a=(int) arr.get(i);
                int b=(int) arr.get(j);
                int t=42-a-b;
                if(0<t&&t<=27&&arr.contains(t)){
                    if(collinear(a,b)){                       
                        count=count+1;
                    } 
                }
            }
        }
        return (count/3);
    }
    static int combination(char[][][] game,ArrayList arr, int n){// to store positions in an array which are capable of making collinear lines
        ArrayList<Integer> array = new ArrayList<Integer>();
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                int a=(int) arr.get(i);
                int b=(int) arr.get(j);
                int t=42-a-b;
                if(0<t&&t<=27){
                    int p[]=getIndex(t,magicCube.MagicCube());  
                    if(game[p[0]][p[1]][p[2]]=='E'){
                    if(collinear(a,b)){
                        array.add(t);
                    }
                    }
                }
            }
        }
        if(!array.isEmpty()){
            int[] best=bestInsertCondition(game,arr,array);
            return best[0];
        }
        else
        return -1;
       
    }
   
   
    public static int[] getIndex(int x, int[][][] arr){// to return the index of the required position
        int p[]=new int[3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    if(x==arr[i][j][k]){
                        p[0]=i;
                        p[1]=j;
                        p[2]=k;
                        return p;
                    }
                }
            }
        }
        return p;
    }
    public static void printBoard(char[][][] arr){//to print the board
        for (int i = 0; i < 3; i++) {
            System.out.println("Layer:"+(i+1));
            System.out.println();
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k <3; k++) {
                        if(arr[i][j][k]==14)
                         System.out.print(i+" "+j+" "+k+" ");
                         System.out.print(arr[i][j][k]+" ");
                            }
                            System.out.println();
                            System.out.println();
                                       
}
}
    }
   
    public static void main(String[] args)
    {
        magicCube m=new magicCube();//3D Array which has the magic cube values
        int magicCube[][][]=m.MagicCube();//To Print the magic cube for reference
        for (int i = 0; i < 3; i++) {//to print magic cube for reference
            System.out.println("Layer:"+(i+1));
            System.out.println();
for (int j = 0; j < 3; j++) {
for (int k = 0; k <3; k++) {
System.out.print(magicCube[i][j][k]+" ");
}
                                System.out.println();
                                System.out.println();
}
}
        Scanner sc=new Scanner(System.in);
        ArrayList<Integer> CList = new ArrayList<>();//Maintains the list of computer's blocks in which it has played.
        ArrayList<Integer> HList = new ArrayList<>();//Maintains the list of Human's blocks in which it has played.
        int cCount=0;//contains the count of collinear lines made by computer
        int hCount=0;//contains the count of collinear lines made by human
        int cTimes=0;//number of times computer has played
        int hTimes=0;//number of times human has played
        int winCount=10;//number of colliner lines needed to win the game
        char turn;//to decide whose chance is it to play(computer or human)
       
        int p[]=new int[3];
        char game[][][]=new char[3][3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    game[i][j][k]='E';
                }
            }
        }
        printBoard(game);//to print empty board
        System.out.println("If you would like to play first type y else type n");
        char c=sc.next().charAt(0);
        int input=0;
        if(c=='y'){//if the player wants to play first
            System.out.println("Start the game");
            hTimes=hTimes+1;
            input=sc.nextInt();
            p=getIndex(input,magicCube);
            game[p[0]][p[1]][p[2]]='X';
            HList.add(input);
            printBoard(game);
            printCount(game,CList,CList.size(),'O');
            printCount(game,HList,HList.size(),'H');
           
            turn='c';
           
        }
        else{//if the player wants computer to play first
            cTimes=cTimes+1;
            p=getIndex(14,magicCube);
            game[p[0]][p[1]][p[2]]='O';
            CList.add(14);
            printBoard(game);
            printCount(game,CList,CList.size(),'O');
            printCount(game,HList,HList.size(),'H');
           
            turn='h';
        }
        while(true){
            if(isFull(game)){//to check if the game board is full and break from the while loop
                System.out.println("ITS A DRAW TRY AGAIN");
                break;
            }
            if(turn=='c'){//to check if its computer's turn
              
                if(hTimes<=1){// to check if the computer has not played or if the computer has played only once 
                    if(game[1][1][1]=='E'){//places O in the centre of the board if it is empty
                        cTimes=cTimes+1;
                        p=getIndex(14,magicCube);
                        game[p[0]][p[1]][p[2]]='O';
                        CList.add(14);
                        printBoard(game);
                        printCount(game,CList,CList.size(),'O');
                        printCount(game,HList,HList.size(),'H');                       
                        turn='h';  
                    }
                    else{//else places it in the corner
                        int b=insertCorner(game);
                        cTimes=cTimes+1;
                        p=getIndex(b,magicCube);
                        game[p[0]][p[1]][p[2]]='O';
                        CList.add(b);
                        printBoard(game);
                        printCount(game,CList,CList.size(),'O');
                        printCount(game,HList,HList.size(),'H');
                       
                       
                       
                        turn='h';  
                    }
                    continue;
                }
                if(hTimes>1){//if human has played more than once(then winning conditions have to be checked)
                    
                    if(hCount==9&&(cCount<9)){//if collinear lines made by human is 9 and computer is less than 9 then first computer has to block human's winning condition 
                        int n=HList.size();
                        int w = winChance(game,HList, n);
                        if(w==-1){
                            n=CList.size();
                            w = winChance(game,CList, n);
                           if(w==-1){
                               int[] best=insertBest(game,CList);
                                cTimes=cTimes+1;
                                        p=getIndex(best[0],magicCube);
                                        game[p[0]][p[1]][p[2]]='O';
                                        CList.add(best[0]);
                                        printBoard(game);
                                        
                                        printCount(game,CList,CList.size(),'O');
                                        printCount(game,HList,HList.size(),'H');
                                        cCount=countLines(game,CList,CList.size());
                                        if(cCount>=winCount){
                                            System.out.println("COMPUTER IS THE WINNER");
                                            break;
                                        }
                                        turn='h';
                            }
                                else{
                                    cTimes=cTimes+1;
                                    p=getIndex(w,magicCube);
                                    game[p[0]][p[1]][p[2]]='O';
                                    CList.add(w);
                                    cCount=countLines(game,CList,CList.size());
                                    printBoard(game);
                                    printCount(game,CList,CList.size(),'O');
                        printCount(game,HList,HList.size(),'H');
                                    if(cCount>=winCount){
                                        System.out.println("COMPUTER IS THE WINNER");
                                        break;
                                    }
                                    turn='h';
                                }
                        }
                        else{
                                    cTimes=cTimes+1;
                                    
                                    p=getIndex(w,magicCube);
                                    game[p[0]][p[1]][p[2]]='O';
                                    CList.add(w);
                                    cCount=countLines(game,CList,CList.size());
                                    printBoard(game);
                                    printCount(game,CList,CList.size(),'O');
                                    printCount(game,HList,HList.size(),'H');
                                    if(cCount>=winCount){
                                        System.out.println("COMPUTER IS THE WINNER");
                                        break;
                                    }
                                    turn='h'; 
                        }
                    }
                    else{//if number of collinear lines made by human is less than 9 or if number of collinear lines made by computer is 9(in this case first winning condition of computer has to checked)
                        int n=CList.size();
                        int w = winChance(game,CList,n);
                        if(w==-1){//w is -1 when there is no chance for computer to make a collinear line
                            n=HList.size();
                            w = winChance(game,HList, n);
                            if(w==-1){//w is -1 when there is no chance for human to make a collinear line
                                int[] best=insertBest(game,CList);//if there is no chance for computer to make a collinear line and if there is no chance for human to make a collinear line in next turn then O is inserted in best position
                                cTimes=cTimes+1;
                                        p=getIndex(best[0],magicCube);
                                        game[p[0]][p[1]][p[2]]='O';
                                        CList.add(best[0]);
                                        cCount=countLines(game,CList,CList.size());
                                        printBoard(game);
                                        
                                        printCount(game,CList,CList.size(),'O');
                                        printCount(game,HList,HList.size(),'H');
                                        if(cCount>=winCount){//if number of collinear lines made by computer is equal to or more than 10 then it is decalred as the winner and game is stopped
                                            System.out.println("COMPUTER IS THE WINNER");
                                            break;
                                        }
                                        
                                        
                                        turn='h';
                                
                            }
                                else{//if there is a chance for computer to make a collinear line then w contains than position and computer blocks that position
                                    cTimes=cTimes+1;
                                    
                                    p=getIndex(w,magicCube);
                                    game[p[0]][p[1]][p[2]]='O';
                                    CList.add(w);
                                    printBoard(game);
                                    printCount(game,CList,CList.size(),'O');
                                    printCount(game,HList,HList.size(),'H');
                                    cCount=countLines(game,CList,CList.size());
                                    if(cCount>=winCount){
                                        System.out.println("COMPUTER IS THE WINNER");
                                        break;
                                    }
                                    
                                    
                                    turn='h';
                                }
                            
                        }
                        else{//if computer has a chance to make a collinear line 
                            cTimes=cTimes+1;
                            p=getIndex(w,magicCube);
                            game[p[0]][p[1]][p[2]]='O';
                            CList.add(w);
                            printBoard(game);
                            printCount(game,CList,CList.size(),'O');
                        printCount(game,HList,HList.size(),'H');
                        cCount=countLines(game,CList,CList.size());
                
                            if(cCount>=winCount){
                                System.out.println("COMPUTER IS THE WINNER");
                                break;
                            }
                            turn='h';
                        }
                    }
                }
           
            }
            else{//human's chance
                System.out.println("Your Turn");
                do{//to make sure that computer enters a valid and empty position 
                   
                    input=sc.nextInt();
                    p=getIndex(input,magicCube);
                    if(game[p[0]][p[1]][p[2]]!='E'){
                        System.out.println("Enter another number");
                    }
                }while(game[p[0]][p[1]][p[2]]!='E');
                hTimes=hTimes+1;
                game[p[0]][p[1]][p[2]]='X';
                HList.add(input);
                printBoard(game);
                printCount(game,CList,CList.size(),'O');
                printCount(game,HList,HList.size(),'H');        
                p=getIndex(input,magicCube);               
                hCount=countLines(game,HList,HList.size());              
                if(hCount>=winCount){//to check if human has won 
                    System.out.println("HUMAN IS THE WINNER");
                    break;
                }
                else{
                    turn='c';//to give the turn to computer
                    continue;
                }
               
            }
        }
    }
   
}








































































































