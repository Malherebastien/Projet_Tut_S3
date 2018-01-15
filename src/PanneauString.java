public class PanneauString extends Panneau
{
    private String[][] tab;

    public PanneauString(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new String[nbLig][nbCol];
    }



    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = value;

    }

    public String getValue(int lig, int col)
    {
        return tab[lig][col];
    }

    public String[][] getTab()
    {
        return tab;
    }
}

