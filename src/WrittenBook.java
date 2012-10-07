import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Interface to books
 * @author Brian McCarthy
 *
 */
public class WrittenBook {

	OItemStack book;

	/**
	 * Takes an item - this should be a instance of a book and quill or written book
	 * Use isValid() to make sure the item is a book
	 * Theoretically it is possible for any item to contain the correct tags that make it seam like a book
	 * However this can not happen with out 3rd party interference 
	 * @param item
	 * @see #isValid()
	 */
	public WrittenBook(Item item) {
		this.book = item.getBaseItem();
	}
	
	/**
	 * Takes the OItemStack for this book
	 * @param book
	 */
	public WrittenBook(OItemStack book) {
		this.book = book;
	}

	/**
	 * Get the title of this book
	 * @return empty string ("") if the NBTTagCompound is null or the NBTTagCompund does not contain the "title" tag (usually when item is not a book)
	 */
	public String getTitle() {
		if (book.d == null) {
			return "";
		}
		if (!book.d.b("title")) {
			return "";
		}
		return book.d.i("title");
	}
	
	public void setTitle(String title) {
		book.d.a("title", title);
	}

	/**
	 * Get the author of this book
	 * @return empty string ("") if the NBTTagCompound is null or the NBTTagCompund does not contain the "author" tag (usually when item is not a book)
	 */
	public String getAuthor() {
		if (book.d == null) {
			return "";
		}
		if (!book.d.b("author")) {
			return "";
		}
		return book.d.i("author");
	}
	
	public void setAuthor(String author) {
		book.d.a("author", author);
	}

	public List<String> getPages() {
		if (book.d == null) {
			return Collections.emptyList();
		}
		List<String> pages = new ArrayList<String>();
		ONBTTagList nbtPages = (ONBTTagList) book.d.a("pages");
		for (int i = 0; i < nbtPages.c(); i++) {
			ONBTTagString nbtPage = (ONBTTagString) nbtPages.b(i);
			String page = nbtPage.a;
			if (page != null && !page.trim().equals("")) {
				pages.add(page);
			}
		}
		return pages;
	}
	
	public void setPages(List<String> pages) {
		ONBTTagList nbtPages = new ONBTTagList("pages");
		for (String page : pages) {
			nbtPages.a(new ONBTTagString(page));
		}
		book.d.a("pages", nbtPages);
	}
	
	public boolean iValid() {
		if (book.c == 386 || book.c == 387) {
			return true;
		}
		return false;
	}

}