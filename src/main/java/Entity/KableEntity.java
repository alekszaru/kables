package Entity;

import javax.persistence.*;

@Entity
@Table(name = "kable", schema = "kables")
public class KableEntity {
    private int id;
    private String kableType;
    private Double kableVolume;
    private Double kablePrice;
    private String kableStockCompany;
    private String kableStockActuality;
    private String kableFullName;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KABLE_TYPE", nullable = false, length = 255)
    public String getKableType() {
        return kableType;
    }

    public void setKableType(String kableType) {
        this.kableType = kableType;
    }

    @Basic
    @Column(name = "KABLE_VOLUME", nullable = true)
    public Double getKableVolume() {
        return kableVolume;
    }

    public void setKableVolume(Double kableVolume) {
        this.kableVolume = kableVolume;
    }

    @Basic
    @Column(name = "KABLE_PRICE", nullable = true, precision = 2)
    public Double getKablePrice() {
        return kablePrice;
    }

    public void setKablePrice(Double kablePrice) {
        this.kablePrice = kablePrice;
    }

    @Basic
    @Column(name = "KABLE_STOCK_COMPANY", nullable = false, length = 45)
    public String getKableStockCompany() {
        return kableStockCompany;
    }

    public void setKableStockCompany(String kableStockCompany) {
        this.kableStockCompany = kableStockCompany;
    }

    @Basic
    @Column(name = "KABLE_FULL_NAME", nullable = true, length = 255)
    public String getKableFullName() {
        return kableFullName;
    }

    public void setKableFullName(String kableFullName) {
        this.kableFullName = kableFullName;
    }

    @Column(name = "KABLE_STOCK_ACTUALITY", nullable = false, length = 40)
    public String getKableStockActuality() {
        return kableStockActuality;
    }

    public void setKableStockActuality(String kableStockActuality) {
        this.kableStockActuality = kableStockActuality;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KableEntity that = (KableEntity) o;

        if (id != that.id) return false;
        if (kableType != null ? !kableType.equals(that.kableType) : that.kableType != null) return false;
        if (kableVolume != null ? !kableVolume.equals(that.kableVolume) : that.kableVolume != null) return false;
        if (kablePrice != null ? !kablePrice.equals(that.kablePrice) : that.kablePrice != null) return false;
        if (kableStockCompany != null ? !kableStockCompany.equals(that.kableStockCompany) : that.kableStockCompany != null)
            return false;
        if (kableStockActuality != null ? !kableStockActuality.equals(that.kableStockActuality) : that.kableStockActuality != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (kableType != null ? kableType.hashCode() : 0);
        result = 31 * result + (kableVolume != null ? kableVolume.hashCode() : 0);
        result = 31 * result + (kablePrice != null ? kablePrice.hashCode() : 0);
        result = 31 * result + (kableStockCompany != null ? kableStockCompany.hashCode() : 0);
        result = 31 * result + (kableStockActuality != null ? kableStockActuality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder rezult =new StringBuilder(kableFullName);
        if(!(kableVolume==0)){
            rezult.append("   "+kableVolume.toString()+" м");
        }
        if (!(kablePrice==0)){
            rezult.append(" по "+kablePrice.toString()+" грн");
        }
        rezult.append(" на " + kableStockCompany+ " "+ kableStockActuality);


        return rezult.toString();
    }
}
