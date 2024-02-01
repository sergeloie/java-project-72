@import hexlet.code.dto.url.UrlPage
@import static hexlet.code.util.Utilities.getPrettyDate
@param UrlPage page

@template.layout.page(content = @`
    @if(page.getUrl() != null)
        <section>
        <h1>
            Сайт: ${page.getUrl().getName()}
        </h1>
            <table class="table table-bordered table-hover mt-3">
                <tbody>
                <tr>
                    <td>ID</td>
                    <td>${page.getUrl().getId()}</td>
                </tr>
                <tr>
                    <td>Имя</td>
                    <td>${page.getUrl().getName()}</td>
                </tr>
                <tr>
                    <td>Дата создания</td>
                    <td>${getPrettyDate(page.getUrl().getCreatedAt())}</td>
                </tr>
                </tbody>
            </table>
        </section>
    @endif

`)
