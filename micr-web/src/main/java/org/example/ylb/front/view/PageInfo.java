package org.example.ylb.front.view;

/**
 * @author 邓和颖
 * @since 2023/9/25 17:05
 */

// 分页数据类
public class PageInfo {

    // 页码
    private Integer pageNo;

    // 页面大小
    private Integer pageSize;

    // 总页数
    private Integer totalPage;

    // 总记录数
    private Integer totalRecord;

    public PageInfo() {
    }

    public PageInfo(Integer pageNo, Integer pageSize, Integer totalRecord) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
        this.totalRecord = totalRecord;

        // 计算总页数
        if(this.pageSize > 0) {
            this.totalPage = (int) Math.ceil((double) totalRecord /pageSize);
        }
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }
}
