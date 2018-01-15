public class PanneauDouble extends Panneau
{
    private double[][] tab;

    public PanneauDouble(int nbLig, int nbCol)
    {
        super(nbLig, nbCol);
        tab = new double[nbLig][nbCol];
    }

    public void caseValue(String value, int lig, int col)
    {
        this.tab[lig][col] = Double.parseDouble(value);
    }

    public String getValue(int lig, int col)
    {
        return "" + tab[lig][col];
    }

    public double[][] getTab()
    {
        return tab;
    }
}

