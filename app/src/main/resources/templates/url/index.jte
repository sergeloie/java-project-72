@import hexlet.code.dto.url.UrlsPage
@import hexlet.code.model.Url
@import hexlet.code.util.NamedRoutes
@import static hexlet.code.util.NamedRoutes.getUrlPath
@import static hexlet.code.util.Utilities.getPrettyDate
@param UrlsPage page



@template.layout.page(content = @`
    <table class="table table-striped">
        @for(Url url : page.getUrls())
            <tr>
                <td>
                    ${url.getId()}
                </td>
                <td>
                    <a href="${getUrlPath(url.getId())}">${url.getName()}</a>
                </td>
                <td>
                    ${getPrettyDate(url.getCreatedAt())}
                </td>
            </tr>
        @endfor
    </table>
    @if(page.getUrls().isEmpty())
        <div class="alert alert-info" role="alert">
            There is no any URLs added yet.
        </div>
        <a class="btn btn-primary" href="${NamedRoutes.ROOT_PATH}" role="button">Add some URL</a>
    @endif
`)
