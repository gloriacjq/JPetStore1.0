    </div></div>
    <div id="Footer">

        <div id="PoweredBy">
            &nbsp&nbsp&nbsp<a href="www.csu.edu.cn">www.csu.edu.cn</a>
        </div>

        <div id="Banner">
            <c:if test="${sessionScope.account.bannerOption == true}">
                <a href="viewCategory?categoryId=${sessionScope.account.favouriteCategoryId}">
                        ${sessionScope.account.bannerName}
                </a>
            </c:if>
        </div>
    </div>

</body>

</html>