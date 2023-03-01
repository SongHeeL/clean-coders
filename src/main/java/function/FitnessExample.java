package function;

import fitnesse.responders.run.SuiteResponder;
import fitnesse.wiki.*;

public class FitnessExample {
    public String testableHtml(PageData pageData, boolean includeSuiteSetup) throws Exception {
        return TestableHtmlBuilder(pageData, includeSuiteSetup);
    }

    private String TestableHtmlBuilder(PageData pageData, boolean includeSuiteSetup) throws Exception {
        WikiPage wikiPage = pageData.getWikiPage();
        StringBuffer buffer = new StringBuffer();

        if (isTestPage(pageData)) {
            includeSetups(includeSuiteSetup, wikiPage, buffer);
            buffer.append(pageData.getContent());
            includeTeardowns(includeSuiteSetup, wikiPage, buffer);
        }
        pageData.setContent(buffer.toString());
        return pageData.getHtml();
    }

    private boolean isTestPage(PageData pageData) throws Exception {
        return pageData.hasAttribute("Test");
    }

    private void includeTeardowns(boolean includeSuiteSetup, WikiPage wikiPage, StringBuffer buffer) throws Exception {
        includeInherited(wikiPage, buffer, "TearDown", "teardown");
        if (includeSuiteSetup) {
            includeInherited(wikiPage, buffer, SuiteResponder.SUITE_TEARDOWN_NAME, "teardown");
        }
    }

    private void includeSetups(boolean includeSuiteSetup, WikiPage wikiPage, StringBuffer buffer) throws Exception {
        if (includeSuiteSetup)
            includeInherited(wikiPage, buffer, SuiteResponder.SUITE_SETUP_NAME, "setup");
        includeInherited(wikiPage, buffer, "SetUp", "setup");
    }

    private void includeInherited(WikiPage wikiPage, StringBuffer buffer, String pageName, String mode) throws Exception {
        WikiPage suiteSetup = PageCrawlerImpl.getInheritedPage(pageName, wikiPage);
        if (suiteSetup != null) {
            includePage(wikiPage, buffer, suiteSetup, mode);
        }
    }

    private void includePage(WikiPage wikiPage, StringBuffer buffer, WikiPage suiteSetup, String mode) throws Exception {
        WikiPagePath pagePath = wikiPage.getPageCrawler().getFullPath(suiteSetup);
        String pagePathName = PathParser.render(pagePath);
        buffer.append("!include -" + mode + " .").append(pagePathName).append("\n");
    }
}
