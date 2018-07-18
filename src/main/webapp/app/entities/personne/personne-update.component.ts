import { Component, OnInit, ElementRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { JhiDataUtils } from 'ng-jhipster';

import { IPersonne } from 'app/shared/model/personne.model';
import { PersonneService } from './personne.service';

@Component({
    selector: 'jhi-personne-update',
    templateUrl: './personne-update.component.html'
})
export class PersonneUpdateComponent implements OnInit {
    private _personne: IPersonne;
    isSaving: boolean;

    constructor(
        private dataUtils: JhiDataUtils,
        private personneService: PersonneService,
        private elementRef: ElementRef,
        private activatedRoute: ActivatedRoute
    ) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ personne }) => {
            this.personne = personne;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }

    setFileData(event, entity, field, isImage) {
        this.dataUtils.setFileData(event, entity, field, isImage);
    }

    clearInputImage(field: string, fieldContentType: string, idInput: string) {
        this.dataUtils.clearInputImage(this.personne, this.elementRef, field, fieldContentType, idInput);
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.personne.id !== undefined) {
            this.subscribeToSaveResponse(this.personneService.update(this.personne));
        } else {
            this.subscribeToSaveResponse(this.personneService.create(this.personne));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IPersonne>>) {
        result.subscribe((res: HttpResponse<IPersonne>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
    get personne() {
        return this._personne;
    }

    set personne(personne: IPersonne) {
        this._personne = personne;
    }
}
