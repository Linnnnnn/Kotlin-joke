package com.berlin.android.mykotlin.pojo

/**
 * Created by zhongbolin on 2017/3/10.
 */

class PictureJokeBean {

    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allNum":2903,"allPages":146,"contentlist":[{"ct":"2015-07-30 01:10:29.995","img":"http://img5.hao123.com/data/3_2ec986ed8d235ebb3bd562ed5b782eb6_0","title":"起来！就不~~~","type":2}],"currentPage":1,"maxResult":20}
     */

    var showapi_res_code: Int = 0
    var showapi_res_error: String? = null
    var showapi_res_body: ShowapiResBodyBean? = null

    class ShowapiResBodyBean {
        /**
         * allNum : 2903
         * allPages : 146
         * contentlist : [{"ct":"2015-07-30 01:10:29.995","img":"http://img5.hao123.com/data/3_2ec986ed8d235ebb3bd562ed5b782eb6_0","title":"起来！就不~~~","type":2}]
         * currentPage : 1
         * maxResult : 20
         */

        var allNum: Int = 0
        var allPages: Int = 0
        var currentPage: Int = 0
        var maxResult: Int = 0
        var contentlist: List<ContentlistBean>? = null

        class ContentlistBean {
            /**
             * ct : 2015-07-30 01:10:29.995
             * img : http://img5.hao123.com/data/3_2ec986ed8d235ebb3bd562ed5b782eb6_0
             * title : 起来！就不~~~
             * type : 2
             */

            var ct: String? = null
            var img: String? = null
            var title: String? = null
            var type: Int = 0
        }
    }
}
