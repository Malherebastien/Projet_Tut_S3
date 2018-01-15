public class PanneauInt extends Panneau
{
    private int[][] tab;

    public PanneauInt(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new int[nbLig][nbCol];
    }

    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = Integer.parseInt(value);
    }

    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }

    public int[][] getTab()
    {
        return tab;
    }
}
