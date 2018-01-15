public class PanneauBooleen extends Panneau
{
    private boolean[][] tab;

    public PanneauBooleen(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new boolean[nbLig][nbCol];
    }

    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = Boolean.getBoolean(value);
    }

    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }


}

