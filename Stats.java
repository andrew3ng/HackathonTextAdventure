package player;

public class Stats {
    private double[] stats; // hp, atk, mag, man, stam, spd, def, res, wil;
    private int lv;
    private double[] growthRates;
    private double fluxMod;
    private int exp;
    private int expToNextLv;

    public Stats(double[] baseStats, double[] growthRates) {
        stats = baseStats;
        this.growthRates = growthRates;
        lv = 1;
        exp = 0;
        expToNextLv = 10;
    }

    public void lvUp() {
        lv++;
        fluxMod = calcFluxMod();
        for (int i = 0; i < stats.length; i++) {
            stats[i] += growthRates[i] * (fluxMod);
        }
        expToNextLv += calcLvUp();
    }

    public double calcFluxMod() {
        return (((lv % 2) + ((2 * lv) % 3)) / 2) + .5;
    }

    public int calcLvUp() {
        return (expToNextLv / lv) + (int) Math.log((expToNextLv) * Math.pow(lv, 1.875)) + 5;
    }

    @Override
    public String toString() {
        String s = "Lv: " + lv + "\n";
        for (int i = 0; i < stats.length; i++) {
            if (i != stats.length - 1)
                s += (int) stats[i] + "/";
            else {
                s += (int) stats[i] + "\n";
            }
        }
        s += "Exp: " + exp + "\n";
        return s;
    }

}
