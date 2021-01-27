package ItemOp;

public class ItemSpec {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ItemSpec(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemSpec) {
            return this.getId().equals(((ItemSpec)obj).getId());
        }else{
            return false;
        }
    }
}
