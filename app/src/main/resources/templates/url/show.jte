@import hexlet.code.dto.url.UrlPage
@param UrlPage page

@template.layout.page(content = @`
    @if(page.getUrl() != null)
        <div>
            ${page.getUrl().getName()}
        </div>
        <div>
            ${page.getUrl().getCreatedAt().toGMTString()}
        </div>
    @endif
`)
