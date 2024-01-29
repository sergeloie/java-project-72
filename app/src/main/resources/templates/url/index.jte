@import hexlet.code.dto.url.UrlsPage
@import hexlet.code.model.Url
@param UrlsPage page

@template.layout.page(content = @`
    <table class="table table-striped">
        @for(Url url : page.getUrls())
            <tr>
                <td>
                    ${url.getId()}
                </td>
                <td>
                    ${url.getName()}
                </td>
                <td>
                    ${url.getCreatedAt().toString()}
                </td>
            </tr>
        @endfor
    </table>
`)
