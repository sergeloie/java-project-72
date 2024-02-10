@import hexlet.code.dto.url.UrlPage
@import hexlet.code.dto.urlcheck.UrlChecksPage
@import hexlet.code.util.NamedRoutes
@import static hexlet.code.util.Utilities.getPrettyDate
@param UrlPage page
@param UrlChecksPage urlChecksPage

@template.layout.page(content = @`

    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif

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
            <div>
                <form  action="${NamedRoutes.getUrlCheck(page.getUrl().getId())}" method="post">
                    <input type="submit" class="btn btn-primary mb-3" value="Запустить проверку">
                </form>
            </div>
        </section>

        <section>
            <h1>
                Проверки
            </h1>
            <table class="table table-bordered table-hover mt-3">
                <thead>
                <tr>
                    <th class="col-1">ID</th>
                    <th class="col-1">Код ответа</th>
                    <th>title</th>
                    <th>h1</th>
                    <th>description</th>
                    <th class="col-2">Дата проверки</th>
                </tr>
                </thead>
                <tbody>
                <!-- Здесь можно добавить строки таблицы -->
                @if (!urlChecksPage.getUrlChecks().isEmpty())
                    @for (var urlCheck : urlChecksPage.getUrlChecks())
                        <tr>
                            <td>${urlCheck.getId()}</td>
                            <td>${urlCheck.getStatusCode()}</td>
                            <td>${urlCheck.getTitle()}</td>
                            <td>${urlCheck.getH1()}</td>
                            <td>${urlCheck.getDescription()}</td>
                            <td>${getPrettyDate(urlCheck.getCreatedAt())}</td>
                        </tr>
                    @endfor
                    
                @endif
                </tbody>
            </table>
        </section>
    @endif
    
    

`)
