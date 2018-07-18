import { NgModule } from '@angular/core';

import { Demo2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent } from './';

@NgModule({
    imports: [Demo2SharedLibsModule],
    declarations: [JhiAlertComponent, JhiAlertErrorComponent],
    exports: [Demo2SharedLibsModule, JhiAlertComponent, JhiAlertErrorComponent]
})
export class Demo2SharedCommonModule {}
