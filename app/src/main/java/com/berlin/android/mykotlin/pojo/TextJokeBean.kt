package com.berlin.android.mykotlin.pojo

/**
 * Created by zhongbolin on 2017/3/10.
 */

class TextJokeBean {


    /**
     * showapi_res_code : 0
     * showapi_res_error :
     * showapi_res_body : {"allNum":933,"allPages":47,"contentlist":[{"ct":"　2015-07-10 05:54:00.000","text":"　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br></br>\r\n　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。","title":"你TM现在在我心里就是个菩萨"}],"currentPage":1,"maxResult":20}
     */

    var showapi_res_code: Int = 0
    var showapi_res_error: String? = null
    var showapi_res_body: ShowapiResBodyBean? = null

    class ShowapiResBodyBean {
        /**
         * allNum : 933
         * allPages : 47
         * contentlist : [{"ct":"　2015-07-10 05:54:00.000","text":"　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br></br>\r\n　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。","title":"你TM现在在我心里就是个菩萨"}]
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
             * ct : 　2015-07-10 05:54:00.000
             * text : 　　男生拉着女生沮丧着脸说，再给我一次机会，求求你！说好不提分手的！<br></br>
             * 　　女生甩开男孩的手说：你TM现在在我心里就是个菩萨，除了拜拜我什么都不想做。
             * title : 你TM现在在我心里就是个菩萨
             */

            var ct: String? = null
            var text: String? = null
            var title: String? = null
        }
    }
}
