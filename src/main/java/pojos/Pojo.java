package pojos;

import org.bson.types.ObjectId;

public abstract class Pojo {
    ObjectId id;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

}
