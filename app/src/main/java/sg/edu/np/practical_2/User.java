package sg.edu.np.practical_2;

public class User {
    public String Name;
    public String Description;
    public int Id;
    public boolean Followed;

    public User(String n, String desc, int id, boolean f){
        this.Name = n;
        this.Description = desc;
        this.Id = id;
        this.Followed = f;
    }

    public String getName() {
        return Name;
    }

    public String getDescription() {
        return Description;
    }

    public int getId() {
        return Id;
    }

    public boolean isFollowed() {
        return Followed;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setFollowed(boolean followed) {
        Followed = followed;
    }
}
