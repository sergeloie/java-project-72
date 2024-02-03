@import hexlet.code.dto.url.BuildUrlPage
@import hexlet.code.util.NamedRoutes
@param BuildUrlPage page = null

@template.layout.page(content = @`
    <h1 class="text-center">Page Analyzer</h1>

    @if(page != null && page.getFlash() != null)
        <div class="alert alert-${page.getFlashType()}" role="alert">
            ${page.getFlash()}
        </div>
    @endif

    <div>
    <form action="${NamedRoutes.URL_PATH}" method="post">
        <div class="input-group mb-3">
            <span class="input-group-text" id="basic-addon1">Input site name</span>
            <input type="text" name="url" class="form-control" placeholder="www.example.com" aria-label="Site Name" aria-describedby="basic-addon1">
        </div>
        <input type="submit" class="btn btn-primary mb-3" value="Submit" />
    </form>
    </div>
`)