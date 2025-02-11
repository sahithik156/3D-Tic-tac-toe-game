
package tictactoe;

/**
 *
 * @author akank
 */

    
public class magicCube {
    
   
    public static int correctIndex(int x){//to set the index in such a way that it is valid
        if(x==-1)
            return 2;
        if(x==3)
            return 0;
        return x;
    }
    public static int[][][] MagicCube(){
        int [][][]m=new int[3][3][3];
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                for(int k=0;k<3;k++){
                    m[i][j][k]=0;
                }
            }
        }
        int l=1;
        int r=2;
        int c=1;
        
        for(int i=0;i<27;i++){
            m[l][r][c]=i+1;
            r++;
            r=correctIndex(r);
            c--;
            c=correctIndex(c);
            if(m[l][r][c]!=0){
                c++;
                c=correctIndex(c);
                l--;
                l=correctIndex(l);
                if(m[l][r][c]!=0){
                    r++;
                    r=correctIndex(r);
                    l++;
                    l=correctIndex(l);
                }
            }
        }
        return m;
        
        
        
    }
    
   

    
    
}
