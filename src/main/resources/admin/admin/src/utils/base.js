const base = {
    get() {
        return {
            url : "http://localhost:8080/springboot2j8v774a/",
            name: "springboot2j8v774a",
            // 退出到首页链接
            indexUrl: 'http://localhost:8080/springboot2j8v774a/front/dist/index.html'
        };
    },
    getProjectName(){
        return {
            projectName: "基于springboot的计算机技术学习资源共享平台"
        } 
    }
}
export default base
