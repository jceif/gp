package cn.jf.common;
import java.io.Serializable;
import java.util.List;

public class PageUtil<T> implements Serializable {
    public static final int DEFAULT_PAGE_SIZE = 10;
    private int count;     //总条数
    private int pageSize;   //每页显示条数
    private int pageCount;    //总页数
    private int currentPage;    //当前页
    private int previousPage;    //上一页
    private int nextPage;         //下一页
    private int startRecord;      //开始页
    private int endRecord;        //结束页
    private List<T> records;//对象

    /**
     * 默认组页数
     */
    public static final int DEFAULT_GROUP_SIZE = 7;

    /**
     * 组页数
     */
    private int groupSize = DEFAULT_GROUP_SIZE;

    /**
     * 首页
     */
    private int homePage;

    /**
     * 末页
     */
    private int lastPage;

    /**
     * 开始页码
     */
    private int startPage;

    /**
     * 终止页码
     */
    private int endPage;

    public PageUtil(String current, int count, String pageSize) {
        this.count = count;
        init(current, pageSize);
    }

    private void init(String current, String size) {
        initPageSize(size);
        initPageCount();
        initCurrentPage(current);
        initPreviousPage();
        initNextPage();
        initStartRecord();
        initEndRecord();

        // @homePage
        if (currentPage > 1 && pageCount > 1) {
            homePage = 1;
        }

        // @lastPage
        if (currentPage < pageCount) {
            lastPage = pageCount;
        }

        // @startPage @endPage
        int tmp = currentPage / groupSize;
        if (currentPage == 0 || currentPage % groupSize != 0) {
            tmp++;
        }
        startPage = (tmp - 1) * groupSize + 1;
        endPage = startPage + groupSize - 1;
        if (endPage > pageCount) {
            endPage = pageCount;
        }
    }

    private void initPageSize(String size) {
        if (size == null || size.trim().isEmpty()) {
            pageSize = DEFAULT_PAGE_SIZE;
            return;
        }
        try {
            pageSize = Integer.parseInt(size);
        } catch (Exception e) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
    }

    private void initEndRecord() {
        endRecord = startRecord + pageSize - 1;
        if (endRecord > count) {
            endRecord = count;
        }
    }

    private void initStartRecord() {
        startRecord = (currentPage - 1) * pageSize;
        if (startRecord < 0) {
            startRecord = 0;
        }
        if (startRecord > count) {
            startRecord = count;
        }
    }

    private void initNextPage() {
        //nextPage = currentPage + 1;
        //if (nextPage > pageCount) {
        //    nextPage = pageCount;
        //}
        if (currentPage < pageCount) {
            nextPage = currentPage + 1;
        }
    }

    private void initPreviousPage() {
        //previousPage = currentPage - 1;
        //if (previousPage < 1) {
        //    previousPage = 1;
        //}
        if (currentPage > 1) {
            previousPage = currentPage - 1;
        }
    }

    private void initCurrentPage(String current) {
        if (current == null || current.trim().isEmpty()) {
            currentPage = 1;
            return;
        }
        try {
            currentPage = Integer.parseInt(current);
        } catch (Exception e) {
            currentPage = 1;
        }
        if (currentPage < 1) {
            currentPage = 1;
            return;
        }
        if (currentPage > pageCount) {
            currentPage = pageCount;
        }
    }

    private void initPageCount() {
        pageCount = count / pageSize;
        if (count % pageSize != 0) {
            pageCount++;
        }
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getCount() {
        return count;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public int getStartRecord() {
        return startRecord;
    }

    public int getEndRecord() {
        return endRecord;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    public int getHomePage() {
        return homePage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public int getStartPage() {
        return startPage;
    }

    public int getEndPage() {
        return endPage;
    }
}
