public class PanneauLong extends Panneau
{
    private long[][] tab;

    public PanneauLong(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new long[nbLig][nbCol];
    }

    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = Long.parseLong(value);
    }

    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }

    public long[][] getTab()
    {
        return tab;
    }
}
