package commons;

import java.util.List;

public class Page {

    private List records;   // 存放分页的数据
    private int currentPageNum; // 当前页码
    private int totalPage; // 总页数
    private int pageSize = 10; // 每页显示的数量

    private int totalRecords; // 总记录条数
    private int startIndex;     // 每页记录开始的索引

    private int nextPageNum;    // 下一页
    private int prePageNum;     // 上一页

    private int startPage;  // 开始页
    private int endPage;    // 结束页

    private  String uri;    // 查询分页的地址
    private int showPageCount = 5;  // 显示几个页码(设置为基数)

    //
    public Page(int currentPageNum, int totalRecords){
        this.currentPageNum = currentPageNum;
        this.totalRecords = totalRecords;
        // 计算总页数
        totalPage = totalRecords%pageSize==0?totalRecords/pageSize:totalRecords/pageSize+1;
        //计算每页开始记录的索引
        startIndex = (currentPageNum-1)*pageSize;

        // 计算开始页码和结束页码: 与当前页码有关
        /**
         * 总共10页数据
         * 默认  1 2 3 4 5
         * 2    1 2 3 4 5
         * 4    2 3 4 5 6
         * 7    5 6 7 8 9
         * 9    6 7 8 9 10
         */

        if (totalPage > showPageCount){
            startPage = currentPageNum - showPageCount / 2;
            endPage = currentPageNum + showPageCount / 2;
            if (startPage < 1){
                startPage = 1;
                endPage = showPageCount;
            }

            if (endPage>totalPage){
                endPage = totalPage;
                startPage = totalPage-showPageCount + 1;
            }
        }else {
            startPage = 1;
            endPage = totalPage;
        }
    }

    public List getRecords() {
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getCurrentPageNum() {
        return currentPageNum;
    }

    public void setCurrentPageNum(int currentPageNum) {
        this.currentPageNum = currentPageNum;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getNextPageNum() {
        nextPageNum = currentPageNum + 1;
        if (nextPageNum > totalPage){
            nextPageNum = totalPage;
        }
        return nextPageNum;
    }

    public void setNextPageNum(int nextPageNum) {

        this.nextPageNum = nextPageNum;
    }

    public int getPrePageNum() {
        prePageNum = currentPageNum - 1;
        if (prePageNum < 1){
            prePageNum = 1;
        }
        return prePageNum;
    }

    public void setPrePageNum(int prePageNum) {
        this.prePageNum = prePageNum;
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getShowPageCount() {
        return showPageCount;
    }

    public void setShowPageCount(int showPageCount) {
        this.showPageCount = showPageCount;
    }
}
