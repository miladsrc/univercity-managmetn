package rational;

public class Rational {

    private int num;
    private int dem;

    //Constructor

    public Rational() {
        this.num = 1;
        this.dem = 0;
    }

    //Get and Set

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getDem() {
        return dem;
    }

    public void setDem(int dem) {
        this.dem = dem;
    }


    public Rational(int x, int y) {
        int g = gcd ( Math.abs ( x ), Math.abs ( y ) );
        num = x / g;
        dem = Math.abs ( y ) / g;
        if (y < 0) num = -num;
    }

    private int gcd(int x, int y) {
        int r = x % y;
        while (r != 0) {
            x = y;
            y = r;
            r = x % y;
        }
        return y;
    }

    //Method

    public Rational add(Rational other) {
        Rational temp = new Rational ();

        temp.num = num * other.dem + dem * other.num;
        temp.dem = dem * other.dem;

        int num = temp.num;
        int dem = temp.dem;

        Rational returnTemp = new Rational ( num, dem );
        return returnTemp;
    }


    public Rational sub(Rational other) {
        Rational temp = new Rational ();

        temp.num = num * other.dem - dem * other.num;
        temp.dem = dem * other.dem;

        int num = temp.num;
        int dem = temp.dem;

        Rational returnTemp = new Rational ( num, dem );
        return returnTemp;
    }

    public Rational mul(Rational other) {
        Rational temp = new Rational ();

        temp.num = num * other.num;
        temp.dem = dem * other.dem;

        int num = temp.num;
        int dem = temp.dem;

        Rational returnTemp = new Rational ( num, dem );
        return returnTemp;
    }

    public Rational div(Rational other) {
        Rational temp = new Rational ();

        temp.num = num * other.dem;
        temp.dem = dem * other.num;

        int num = temp.num;
        int dem = temp.dem;

        Rational returnTemp = new Rational ( num, dem );
        return returnTemp;
    }

    public double toFloatingPoint(Rational other) {
        float num = other.num;
        float dem = other.dem;
        float result = num / dem;
        return result;
    }

    public String mixedNumber(Rational other) {
        int a = other.getNum () / other.getDem ();
        int b = other.getNum () % other.getDem ();
        return a != 0 ? (a + "+" + b + "/" + other.getDem ()) : (b + "/" + other.getNum ());
    }

    @Override
    public String toString() {
        return "Rational{" +
                "" + num +
                "/" + dem +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) return false;
        Rational rational = (Rational) obj;
        return num == rational.num && dem == rational.dem;
    }


    public static void main(String[] args) {

    }
}
