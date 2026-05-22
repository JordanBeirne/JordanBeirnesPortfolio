
public class Main {

    public static void main(String[] args) {
        Article a = new Article(1, "My Article");
        Article b = new Article(2, "Another Article");
        a.setContent("A Original contents");
        b.setContent("B has contents too");
        System.out.println(a);
        System.out.println(b);
        ArticleMemento a1 = a.createMemento();
        ArticleMemento b1 = b.createMemento();
        a.setContent("A New contents");
        b.setContent("B second contents!");
        System.out.println(a);
        System.out.println(b);
        a.restore(a1);
        ArticleMemento b2 = b.createMemento();
        b.setContent("B third content");
        System.out.println(a);
        System.out.println(b);
        b.restore(b2);
        System.out.println(b);
        b.restore(b1);
        System.out.println(b);
    }
}

class Article {

    private long id;
    private String title;
    private String content;

    public Article(long id, String title) {
        this.id = id;
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ArticleMemento createMemento() {
        ArticleMemento m = new ArticleMemento(id, title, content);
        return m;
    }

    public void restore(ArticleMemento m) {
        this.id = m.getId();
        this.title = m.getTitle();
        this.content = m.getContent();
    }

    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content=" + content + "]";
    }
}

final class ArticleMemento {

    private final long id;
    private final String title;
    private final String content;

    public ArticleMemento(long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}
