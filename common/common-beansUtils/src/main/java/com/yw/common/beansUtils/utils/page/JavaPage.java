package com.yw.common.beansUtils.utils.page;

import java.util.ArrayList;
import java.util.List;

import com.yw.common.beansUtils.utils.enums.ConfigurationEnum;

/**
 *<pre>
 * 功       能: JAVA LIST分页(参考：http://blog.csdn.net/competerh_programing/article/details/6831065)
 * 涉及版本: 
 * 创  建  者: 陈林林(Vickey)
 * 日       期: 2015-8-14下午3:11:13
 * Q    Q: 308053847
 *</pre>
 */
public class JavaPage<T> { 
	
    private Integer pageNo = 1; // 当前页 

    public Integer totalPage = 0; // 总页数 

    private Integer pageSize;// 每页指定条数据 

    private Integer totalCount = 0; // 总数据数 

    private Integer start = 0;// 每页的起始数 

    private Integer pageEndRow = 0; // 每页显示数据的终止数 

    private boolean hasNextPage = false; // 是否有下一页 

    private boolean hasPreviousPage = false; // 是否有前一页 

    private List<T> list; 


    public JavaPage(List list, Integer pageSize) { 
        init(list, pageSize);// 通过对象集，记录总数划分 
    } 
    
    /** *//** 
     * 初始化list，并告之该list每页的记录数 
     * @param list 
     * @param pageSize 
     */ 
    public void init(List list, Integer pageSize) { 
    	if (pageSize == null || pageSize <= 0) {
    		pageSize = Integer.valueOf(ConfigurationEnum.PAGE_SIZE.getValue());
		}
        this.pageSize = pageSize; 
        this.list = list; 
        totalCount = list.size(); 
        hasPreviousPage = false; 
        if ((totalCount % pageSize) == 0) { 
            totalPage = totalCount / pageSize; 
        } else { 
            totalPage = totalCount / pageSize + 1; 
        } 

        if (pageNo >= totalPage) { 
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 

        if (totalCount < pageSize) { 
            this.start = 0; 
            this.pageEndRow = totalCount; 
        } else { 
            this.start = 0; 
            this.pageEndRow = pageSize; 
        } 
    } 


    // 判断要不要分页 
    public boolean isNext() { 
        return list.size() > 5; 
    } 

    public void setHasPreviousPage(boolean hasPreviousPage) { 
        this.hasPreviousPage = hasPreviousPage; 
    } 

    public String toString(Integer temp) { 
        String str = Integer.toString(temp); 
        return str; 
    } 

    public void description() { 

        String description = "共有数据数:" + this.getTotalCount() + 

        "共有页数: " + this.getTotalPage() + 

        "当前页数为:" + this.getPageNo() + 

        " 是否有前一页: " + this.isHasPreviousPage() + 

        " 是否有下一页:" + this.isHasNextPage() + 

        " 开始行数:" + this.getStart() + 

        " 终止行数:" + this.getPageEndRow(); 

        System.out.println(description); 
    } 

    public List getNextPage() { 
        pageNo = pageNo + 1; 

        disposePage(); 

        System.out.println("用户凋用的是第" + pageNo + "页"); 
        this.description(); 
        return getObjects(pageNo); 
    } 

    /** *//** 
     * 处理分页 
     */ 
    private void disposePage() { 

        if (pageNo == 0) { 
            pageNo = 1; 
        } 

        if ((pageNo - 1) > 0) { 
            hasPreviousPage = true; 
        } else { 
            hasPreviousPage = false; 
        } 

        if (pageNo >= totalPage) { 
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 
    } 

    public List getPreviousPage() { 

        pageNo = pageNo - 1; 

        if ((pageNo - 1) > 0) { 
            hasPreviousPage = true; 
        } else { 
            hasPreviousPage = false; 
        } 
        if (pageNo >= totalPage) { 
            hasNextPage = false; 
        } else { 
            hasNextPage = true; 
        } 
        this.description(); 
        return getObjects(pageNo); 
    } 

    /** *//** 
     * 获取第几页的内容 
     * 
     * @param pageNo 
     * @return 
     */ 
    public List<T> getObjects(Integer pageNo) { 
        if (pageNo == 0) 
            this.setPageNo(1); 
        else 
            this.setPageNo(pageNo); 
        this.disposePage(); 
        if (pageNo * pageSize < totalCount) {// 判断是否为最后一页 
            pageEndRow = pageNo * pageSize; 
            start = pageEndRow - pageSize; 
        } else { 
            pageEndRow = totalCount; 
            start = pageSize * (totalPage - 1); 
        } 

        List objects = null; 
        if (!list.isEmpty()) { 
            objects = list.subList(start, pageEndRow); 
        } 
        return objects; 
    } 

    public List getFistPage() { 
        if (this.isNext()) { 
            return list.subList(0, pageSize); 
        } else { 
            return list; 
        } 
    } 

    public boolean isHasNextPage() { 
        return hasNextPage; 
    } 


    public void setHasNextPage(boolean hasNextPage) { 
        this.hasNextPage = hasNextPage; 
    } 


    public List getList() { 
        return list; 
    } 


    public void setList(List list) { 
        this.list = list; 
    } 


    public Integer getPageNo() {
		return pageNo;
	}

	public void setPageNo(Integer pageNo) {
		this.pageNo = pageNo;
	}

	public Integer getPageEndRow() { 
        return pageEndRow; 
    } 


    public void setPageEndRow(Integer pageEndRow) { 
        this.pageEndRow = pageEndRow; 
    } 

    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

    public Integer getStart() {
		return start;
	}

	public void setStart(Integer start) {
		this.start = start;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}


    public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public boolean isHasPreviousPage() { 
        return hasPreviousPage; 
    } 
    
    public static void main(String[] args) {
    	List<String> list = new ArrayList<String>(); 
        list.add("a"); 
        list.add("b"); 
        list.add("c"); 
        list.add("d"); 
        list.add("e"); 
        list.add("f"); 
        /* list.add("g"); 
        list.add("h"); 
        list.add("h"); 
        list.add("i"); 
        list.add("j"); 
        list.add("k"); 
        list.add("l"); 
        list.add("m"); */
        JavaPage<String> pm = new JavaPage<String>(list, 15); 
        
        List<String> sublist = pm.getObjects(1); 
        for(int i = 0; i < sublist.size(); i++) { 
            System.out.println(sublist.get(i)); 
        } 
	}

} 