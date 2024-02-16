@import hexlet.code.dto.url.UrlsPage
@import hexlet.code.model.Url
@import hexlet.code.util.NamedRoutes
@import static hexlet.code.util.NamedRoutes.getUrlPath
@import static hexlet.code.util.Utilities.getPrettyDate
@param UrlsPage page



@template.layout.page(content = @`
    <meta about="All urls page">
    <table class="table table-striped">
        <thead>
        <tr>
            <th class="col-1">ID</th>
            <th>Name</th>
            <th class="col-2">Latest Check</th>
            <th class="col-1">Status Code</th>
        </tr>
        </thead>
        @for(Url url : page.getUrls())
            <tr>
                <td>
                    ${url.getId()}
                </td>
                <td>
                    <a href="${getUrlPath(url.getId())}">${url.getName()}</a>
                </td>
                <td>
                    @if(page.getLatestChecks().containsKey(url.getId()))
                    ${getPrettyDate(page.getLatestChecks().get(url.getId()).getCreatedAt())}
                    @endif
                </td>
                <td>
                    @if(page.getLatestChecks().containsKey(url.getId()))
                        ${page.getLatestChecks().get(url.getId()).getStatusCode()}
                    @endif
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
