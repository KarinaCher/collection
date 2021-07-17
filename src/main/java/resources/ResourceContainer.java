package resources;

public class ResourceContainer {
    private static PostcardResource resource = new PostcardResource();
    private static PostcardOtherResource otherResource = new PostcardOtherResource();
    private static TagResource tagResource;

    public static PostcardResource getResource() {
        return resource;
    }

    public static PostcardOtherResource getOtherResource() {
        return otherResource;
    }

    public static TagResource getTagResource() {
        if (tagResource == null) {
            tagResource = new TagResource(getResource());
        }
        return tagResource;
    }
}
