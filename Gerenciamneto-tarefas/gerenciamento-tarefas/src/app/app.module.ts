import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';  // Importando o novo método

import { AppComponent } from './app.component';

@NgModule({
    declarations: [AppComponent],
    imports: [
        BrowserModule,
    ],
    providers: [
        provideHttpClient(withInterceptorsFromDi())  // Usando a nova forma de configurar o HttpClient
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
