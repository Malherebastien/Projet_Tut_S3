public class PanneauChar extends Panneau
{
    private char[][] tab;

    public PanneauChar(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new char[nbLig][nbCol];
    }

    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = value.charAt(0);
    }

    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }

    public char[][] getTab()
    {
        return tab;
    }
}

