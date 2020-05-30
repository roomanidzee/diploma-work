<template>
    <div>

      <div class="file">
          <label class="file-label">
            <input 
              class="file-input"
              type="file"
              name="resume"
              ref="file"
              multiple
              v-on:change="handleFileUpload"/>
            <span class="file-cta">
              <span class="file-icon">
                <i class="fas fa-upload"></i>
              </span>
              <span class="file-label">
                  Загрузить файл
              </span>
            </span>
          </label>
      </div>
      <div v-for="fileInfo in filesData" v-bind:key="fileInfo">
        <card
          v-bind:fileName="fileInfo.path"
          v-bind:fileType="fileInfo.typeInfo"
          v-bind:fileID="fileInfo.idValue"
        />
      </div>
    </div>
</template>

<script lang="ts">
import FilesCard from '@/components/files/files_card';
import { Component, Vue } from 'vue-property-decorator';
import { filesAppClient, serviceAppClient } from '@/components/common/services/clients';

interface FileInfo {
    idValue: string;
    path: string;
    typeInfo: string;
}

@Component({
  components: {
    'card': FilesCard,
  },
})
export default class FilesInfoComponent extends Vue{

  private filesData: FileInfo[] | null = null;

  public mounted(){

    const headers = {
      'Content-Type': 'application/json',
      'Authorization': sessionStorage.getItem('token')
    }

    const resp = serviceAppClient.get('/api/files/internal', headers);

    const filesResult = resp.then((respValue: any) => {
        this.filesData = respValue.data.foreach((elem: any) =>{
          const result : FileInfo = {
            idValue: elem.id,
            path: elem.path,
            typeInfo:  <string> elem.fileType.substring(<string> elem.fileType.lastIndexOf('/'))
          };
        });
    })

  }

  public handleFileUpload(e: Event){
    const file: File = (<HTMLInputElement>e.target).files[0];

    let formData = new FormData();
    formData.append('input_file', file);
    const headers = {
      'Content-Type': 'multipart/form-data',
      'Authorization': sessionStorage.getItem('token')
    }
    filesAppClient.post('/api/files/upload', formData, headers)
                  .then((respValue: any) => {
                    if (respValue.status_code !== 200) {
                      console.error('failed to upload file');
                    }
                    return '';
                  });
  }
}
</script>
