package maskbook.maskshop.service;

import lombok.RequiredArgsConstructor;
import maskbook.maskshop.domain.BookMark;
import maskbook.maskshop.domain.BookMarkMeta;
import maskbook.maskshop.repository.BookMarkMetaRepository;
import maskbook.maskshop.repository.BookMarkRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BookMarkService {

    private final BookMarkRepository bookMarkReposiotry;
    private final BookMarkMetaRepository bookMarkMetaRepository;

    @Transactional
    public void saveUrlData(BookMark bookMark) throws IOException {
        String bookmarkDataUrl = bookMark.getPostUrl();
        BookMarkMeta bmMeta = new BookMarkMeta();
        List<BookMarkMeta> bookMarkList = new ArrayList<>();

        Document doc = Jsoup.connect(bookmarkDataUrl).get();
        String image = doc.select("meta[property=og:image]").first().attr("content");
        String title = doc.select("meta[property=og:title]").first().attr("content");
        String description = doc.select("meta[property=og:description]").first().attr("content");
//        System.out.println("iamge: " + image);
//        System.out.println("title: " + title);
//        System.out.println("description: " + description);

        bmMeta.setImage(image);
        bmMeta.setTitle(title);
        bmMeta.setDescription(description);
        bmMeta.setUserComment(bookMark.getPostComment());

        bookMarkMetaRepository.save(bmMeta);

        }
        public BookMarkMeta findUrl(Long bmtId){
            return bookMarkMetaRepository.findOne(bmtId);
        }

        public List<BookMarkMeta> findUrlData(){
            return bookMarkMetaRepository.findAll();
        }

        @Transactional
        public void cancelUrl(BookMarkMeta bmt){
            bookMarkMetaRepository.cancel(bmt);
        }
}
