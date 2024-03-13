package code.domain;

import java.io.Serializable;
import java.util.Objects;

public class Identifiable<ID> implements Serializable {
    protected ID id;
    Identifiable(ID id){this.id = id;}
    public ID getId(){return this.id;}
    public void setId(ID id){this.id = id;}

    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(!(o instanceof Identifiable<?> that)) return false;
        return Objects.equals(id, that.id);
    }

}

