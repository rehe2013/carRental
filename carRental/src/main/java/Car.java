public class Car {
    private Metadata metadata;

    private Perdayrent perdayrent;

    private String model;

    private String vin;

    private Metrics metrics;

    private String make;

    public Car(String make, String model, String vin, String color, String notes, int price,
        int discount, float yoymaintenancecost, float depreciation, int lastweek, int yeartodate){
        this.make = make;
        this.model = model;
        this.vin = vin;
        this.metadata = new Metadata(color, notes);
        this.perdayrent = new Perdayrent(price, discount);
        this.metrics = new Metrics(yoymaintenancecost, depreciation, lastweek,  yeartodate);
    }

    public Metadata getMetadata ()
    {
        return metadata;
    }

    public void setMetadata (Metadata metadata)
    {
        this.metadata = metadata;
    }

    public Perdayrent getPerdayrent ()
    {
        return perdayrent;
    }

    public void setPerdayrent (Perdayrent perdayrent)
    {
        this.perdayrent = perdayrent;
    }

    public String getModel ()
    {
        return model;
    }

    public void setModel (String model)
    {
        this.model = model;
    }

    public String getVin ()
    {
        return vin;
    }

    public void setVin (String vin)
    {
        this.vin = vin;
    }

    public Metrics getMetrics ()
    {
        return metrics;
    }

    public void setMetrics (Metrics metrics)
    {
        this.metrics = metrics;
    }

    public String getMake ()
    {
        return make;
    }

    public void setMake (String make)
    {
        this.make = make;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [metadata = "+metadata+", perdayrent = "+perdayrent+", model = "+model+", vin = "+vin+", metrics = "+metrics+", make = "+make+"]";
    }

    class Metadata {
        public String Color;
        public String Notes;
        public Metadata(String color, String notes){
            this.Color = color;
            this.Notes = notes;
        }
    }

    class Perdayrent {
        public int Price;
        public int Discount;
        public Perdayrent(int price, int discount){
            this.Price = price;
            this.Discount = discount;
        }
    }

    class Metrics {
        public float yoymaintenancecost;
        public float depreciation;
        public RentalCount rentalcount;
        public Metrics (float yoymaintenancecost, float depreciation, int lastweek, int yeartodate){
            this.yoymaintenancecost = yoymaintenancecost;
            this.depreciation =  depreciation;
            this.rentalcount = new RentalCount(lastweek, yeartodate);
        }
    }

    class RentalCount {
        public int lastweek;
        public int yeartodate;
        public RentalCount (int lastweek, int yeartodate){
            this.lastweek = lastweek;
            this.yeartodate = yeartodate;
        }
    }
}
