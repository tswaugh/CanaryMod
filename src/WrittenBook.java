import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class WrittenBook {

	OItemStack book;

	public WrittenBook(OItemStack book) {
		this.book = book;
	}

	public String getTitle() {
		if (book.d == null) {
			return "";
		}
		if (!book.d.b("title")) {
			return "";
		}
		return book.d.i("title");
	}

	public String getAuthor() {
		if (book.d == null) {
			return "";
		}
		if (!book.d.b("author")) {
			return "";
		}
		return book.d.i("author");
	}

	public List<String> getPages() {
		if (book.d == null) {
			return Collections.emptyList();
		}
		List<String> pages = new ArrayList<String>();
		ONBTTagList nbtPages = (ONBTTagList)book.d.a("pages");
		for (int i = 0; i < nbtPages.c(); i++) {
			ONBTTagString nbtPage = (ONBTTagString)nbtPages.b(i);
			String page = nbtPage.a;
			if (page != null && !page.trim().equals("")) {
				pages.add(page);
			}
		}
		return pages;
	}
	
	public boolean iValid() {
		if (book.c == 386 || book.c == 387) {
			return true;
		}
		return false;
	}

}